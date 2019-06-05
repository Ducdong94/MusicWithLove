package com.cron.service.worker;

import com.cron.service.Configuration.ZingConfig;
import com.cron.service.entity.Category;
import com.cron.service.entity.Playlist;
import com.cron.service.entity.Song;
import com.cron.service.entity.Source;
import com.cron.service.repository.CategoryRepository;
import com.cron.service.repository.PlaylistRepository;
import com.cron.service.repository.SongRepository;
import com.cron.service.repository.SourceRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Configuration
@EnableScheduling
public class ZingMp3Worker {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    PlaylistRepository playlistRepository;
    @Autowired
    SongRepository songRepository;
    @Autowired
    SourceRepository sourceRepository;


    @Scheduled(fixedDelay = 86400000)
    public void top100MobileScheduler() {
        System.out.println("Start crawl top100MobileScheduler ZingMp3 at " + new Date());
        try {
            Document document = Jsoup.connect(ZingConfig.M_TOP100).get();
            Elements elements = document.getElementsByClass("module-allLinks");
            String playlistTitle, playlistId, href;
            Elements links;
            Playlist playlist;
            Category cate;
            for (Element element : elements) {
                playlistTitle = element.getElementsByClass("title-main").text();
                links = element.getElementsByTag("a");
                for (Element link : links) {
                    if (!link.toString().contains("href=\"#\"")) {
                        href = link.attr("href");
                        playlistTitle += link.attr("title");
                        playlistId = href.substring(href.lastIndexOf("/") + 1, href.indexOf("."));
                        playlist = new Playlist(playlistId, playlistTitle);
                        cate = new Category(link.attr("title"));
                        document = Jsoup.connect(ZingConfig.M_DOMAIN + href).get();
                        href = document.getElementsByClass("overlay-video").get(0).getElementsByTag("a").attr("href");
                        document = Jsoup.connect(ZingConfig.M_DOMAIN + href).get();
                        href = document.getElementById("zplayerjs-wrapper").attr("data-source");
                        getSongFromAlbum(href, cate, playlist);
                        return;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void getSongFromAlbum(String path, Category cate, Playlist playlist) {
        try {
            categoryRepository.save(cate);
            playlistRepository.save(playlist);
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
            List<Song> songs = new LinkedList<>();
            List<Source> sources = new LinkedList<>();
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
                System.out.println(id);
                System.out.println(name);
                Song song = new Song(id, name, code, artistsNames, link, thumbnail, duration);
                Source src = new Source("128", source.substring(2));
                src.setSong(song);
                sources.add(src);
                song.setCategories(Arrays.asList(cate));
                song.setSource(Arrays.asList(src));
                song.setPlaylists(Arrays.asList(playlist));
                break;
            }
            cate.setSongs(songs);
            playlist.setPlaylistSongs(songs);
            categoryRepository.save(cate);
            playlistRepository.save(playlist);
            songRepository.saveAll(songs);
            sourceRepository.saveAll(sources);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
