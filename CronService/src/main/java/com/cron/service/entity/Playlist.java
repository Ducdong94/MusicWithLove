package com.cron.service.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Playlist {
    @Id
    private String id;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "playlist_song",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    List<Song> playlistSongs;

    public Playlist() {
    }

    public Playlist(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
