package com.devgd.melonclone.domain.playlist.dao;

import java.util.Optional;

import com.devgd.melonclone.domain.playlist.domain.PlaylistEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<PlaylistEntity, Integer> {
	Optional<PlaylistEntity> findByPlaylistId(Integer playlistId);
}