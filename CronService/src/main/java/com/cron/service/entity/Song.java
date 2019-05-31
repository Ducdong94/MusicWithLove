package com.cron.service.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Song {
    @Id
    private String id;
    @NotNull
    private String title;
    private String code;
    private String artistsNames;
    private String link;
    private String thumbnail;
    private int duration;
    @ManyToOne
    private String source;
    @NotNull
    private long time;
    @ManyToMany(mappedBy = "songs")
    List<Category> categories;
    @ManyToMany(mappedBy = "playlistSongs")
    List<Playlist> playlists;

    public Song(String id, @NotNull String title, String code, String artistsNames, String link, String thumbnail, int duration, String source) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.artistsNames = artistsNames;
        this.link = link;
        this.thumbnail = thumbnail;
        this.duration = duration;
        this.source = source;
        this.time = System.currentTimeMillis();
    }

    public Song() {


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getArtistsNames() {
        return artistsNames;
    }

    public void setArtistsNames(String artistsNames) {
        this.artistsNames = artistsNames;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
