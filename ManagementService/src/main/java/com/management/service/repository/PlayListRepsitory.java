package com.management.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.service.entity.Playlist;
@Repository
public interface PlayListRepsitory extends JpaRepository<Playlist, String> {
	
}
