package com.cron.service.worker;

import com.cron.service.Configuration.ZingConfig;
import com.cron.service.entity.Playlist;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.util.Date;


@Configuration
@EnableScheduling
public class ZingMp3Worker {
    @Scheduled(fixedDelay = 86400000)
    public void dailiesScheduler() {
        System.out.println("Start crawl ZingMp3 at " + new Date());
        try {
            Document document = Jsoup.connect(ZingConfig.M_TOP100).get();
            Elements elements = document.getElementsByClass("module-allLinks");
            String playlistTitle, playlistId, href;
            Elements links;
            for (Element element : elements) {
                playlistTitle = element.getElementsByClass("title-main").text();
                links = element.getElementsByTag("a");
                for (Element link : links) {
                    if (!link.toString().contains("href=\"#\"")) {
                        href = link.attr("href");
                        playlistTitle += link.attr("title");
                        playlistId = href.substring(href.lastIndexOf("/") + 1, href.indexOf("."));
                        Playlist playlist = new Playlist(playlistId, playlistTitle);

                        document = Jsoup.connect(ZingConfig.M_DOMAIN + href).get();
                        href = document.getElementsByClass("overlay-video").get(0).getElementsByTag("a").attr("href");
                        document = Jsoup.connect(ZingConfig.M_DOMAIN + href).get();
                        href = document.getElementById("zplayerjs-wrapper").attr("data-source");
                        getSongFromAlbum(href);
                        return;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void getSongFromAlbum(String path) {
        try {
            Connection.Response response = Jsoup.connect(ZingConfig.M_API + path).method(Connection.Method.GET).execute();
            if (response.statusCode() != HttpURLConnection.HTTP_OK) {
                System.err.println("Cannot connect to " + path);
                return;
            }
            JSONObject jsonObject = new JSONObject(response.body());
            if (jsonObject.getInt("err") != 0) {
                System.err.println("Connect to " + path + " return error_code:" + jsonObject.getInt("err"));
                return;
            }
            JSONArray items = jsonObject.getJSONObject("data").getJSONArray("items");
            String id, name, code, thumbnail, artistsNames, source, link;
            int duration;
            for (int i = 0; i < items.length(); i++) {
                jsonObject = items.getJSONObject(i);
                id = jsonObject.getString("id");
                name = jsonObject.getString("name");
                code = jsonObject.getString("code");
                artistsNames = jsonObject.getString("artists_names");
                link = jsonObject.getString("link");
                thumbnail = jsonObject.getString("thumbnail");
                source = jsonObject.getJSONObject("source").getString("128");
                duration = jsonObject.getInt("duration");
                System.out.println("================");
                System.out.println(id);
                System.out.println(name);
                System.out.println(code);
                System.out.println(artistsNames);
                System.out.println(link);
                System.out.println(thumbnail);
                System.out.println(source);
                System.out.println(duration);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
