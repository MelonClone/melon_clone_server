package com.devgd.melonclone.domain.playlist.dao;

import java.util.List;
import java.util.Optional;

import com.devgd.melonclone.domain.playlist.domain.UserPlaylistEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPlaylistRepository extends JpaRepository<UserPlaylistEntity, Integer> {
	Optional<UserPlaylistEntity> findByUpUserUserId(Integer userId);
	Optional<UserPlaylistEntity> findByUpPlaylistPlaylistId(Integer playlistId);
	List<UserPlaylistEntity> findAllByUpUserUserId(Integer userId);
}