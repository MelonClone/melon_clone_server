package com.devgd.melonclone.domain.music.dao;

import java.util.List;
import java.util.Optional;

import com.devgd.melonclone.domain.music.domain.LyricEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LyricRepository extends JpaRepository<LyricEntity, Integer> {
	Optional<LyricEntity> findByLyricId(Integer lyricId);
	List<LyricEntity> findAllByLyricMusicId(Integer musicId);
}