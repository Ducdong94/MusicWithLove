package com.cron.service.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Song {
    @Id
    private String id;
    @NotNull
    private String title;
    @NotNull
    private String source;
    @NotNull
    private long time;
    @ManyToMany(mappedBy = "songs")
    List<Category> categories;
    @ManyToMany(mappedBy = "playlistSongs")
    List<Playlist> playlists;

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
}
