package com.devgd.melonclone.domain.music.dao;

import java.util.Optional;

import com.devgd.melonclone.domain.music.domain.MusicEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<MusicEntity, Integer> {
	Optional<MusicEntity> findByMusicMusicId(Integer musicId);
}