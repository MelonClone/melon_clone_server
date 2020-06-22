package com.devgd.melonclone.domain.music.dao;

import java.util.Optional;

import com.devgd.melonclone.domain.music.domain.MusicLikeEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicLikeRepository extends JpaRepository<MusicLikeEntity, Integer> {
	Optional<MusicLikeEntity> findByMulIdMulMusicIdAndMulIdMulUserId(String musicId, Integer userId);
	
}