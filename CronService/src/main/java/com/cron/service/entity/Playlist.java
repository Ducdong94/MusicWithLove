package com.cron.service.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String zingId;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "playlist_song",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    List<Song> playlistSongs;

    public Playlist() {
    }

    public Playlist(String zingId, String name) {
        this.zingId = zingId;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getZingId() {
        return zingId;
    }

    public void setZingId(String zingId) {
        this.zingId = zingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getPlaylistSongs() {
        return playlistSongs;
    }

    public void setPlaylistSongs(List<Song> playlistSongs) {
        this.playlistSongs = playlistSongs;
    }
}
