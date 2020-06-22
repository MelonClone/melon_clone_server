package com.devgd.melonclone.domain.music.dao;

import java.util.List;
import java.util.Optional;

import com.devgd.melonclone.domain.music.domain.MusicEntity;
import com.devgd.melonclone.domain.music.domain.MusicId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<MusicEntity, MusicId> {
	Optional<MusicEntity> findByMusicMusicId(String musicId);
	List<MusicEntity> findAllByMusicMusicArtistId(Integer artistId);
}