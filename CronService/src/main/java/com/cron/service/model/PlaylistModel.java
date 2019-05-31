package com.cron.service.model;

import com.cron.service.entity.Playlist;
import com.cron.service.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PlaylistModel {
    @Autowired
    PlaylistRepository playlistRepository;

    public void insertIfNotExist(Playlist playlist) {
        Optional<Playlist> optional = playlistRepository.findById(playlist.getId());
        if (optional.isPresent()) return;
        Playlist playlistDb = playlistRepository.findByName(playlist.getName());
        if (playlist.getName().equals(playlistDb.getName())) return;
        playlistRepository.save(playlist);
    }

}
