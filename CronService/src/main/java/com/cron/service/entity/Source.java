package com.cron.service.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String quality;
    @NotNull
    private String path;
    @ManyToOne
    @JoinColumn
    private Song song;

    public Source() {
    }

    public Source(String quality,String path) {
        this.quality = quality;
        this.path = path;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
