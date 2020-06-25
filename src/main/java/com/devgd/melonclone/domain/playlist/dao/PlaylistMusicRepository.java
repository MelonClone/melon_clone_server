package com.devgd.melonclone.domain.playlist.dao;

import java.util.List;
import java.util.Optional;

import com.devgd.melonclone.domain.playlist.domain.PlaylistMusicEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistMusicRepository extends JpaRepository<PlaylistMusicEntity, Integer> {
	List<PlaylistMusicEntity> findAllByPmPlaylistPlaylistId(Integer playlistId);
	@Query("SELECT pme "+
		"FROM PlaylistMusicEntity pme "+
		"WHERE pme.pmPlaylist.playlistId = :pm_playlist_id ")
	Optional<Integer> findLastOrderById(
		@Param("pm_playlist_id") Integer pmId);
}