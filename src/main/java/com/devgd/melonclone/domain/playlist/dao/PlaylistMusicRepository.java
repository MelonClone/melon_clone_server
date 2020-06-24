package com.devgd.melonclone.domain.playlist.dao;

import java.util.List;

import com.devgd.melonclone.domain.playlist.domain.PlaylistMusicEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistMusicRepository extends JpaRepository<PlaylistMusicEntity, Integer> {
	List<PlaylistMusicEntity> findAllByPmPlaylistPlaylistId(Integer playlistId);
}