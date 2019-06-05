package com.cron.service.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String originId;
    private String title;
    private String code;
    private String artistsNames;
    private String link;
    private String thumbnail;
    private int duration;
    @OneToMany(mappedBy = "song", cascade = {CascadeType.ALL})
    private List<Source> source;
    @NotNull
    private long time;
    @ManyToMany(mappedBy = "songs")
    List<Category> categories;
    @ManyToMany(mappedBy = "playlistSongs")
    List<Playlist> playlists;

    public Song(String originId, String title, String code, String artistsNames, String link, String thumbnail, int duration) {
        this.originId = originId;
        this.title = title;
        this.code = code;
        this.artistsNames = artistsNames;
        this.link = link;
        this.thumbnail = thumbnail;
        this.duration = duration;
        this.time = System.currentTimeMillis();
    }

    public Song() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getoriginId() {
        return originId;
    }

    public void setoriginId(String originId) {
        this.originId = originId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<Source> getSource() {
        return source;
    }

    public void setSource(List<Source> source) {
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
}
