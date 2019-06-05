package com.cron.service.repository;

import com.cron.service.entity.Song;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song, String> {

}
