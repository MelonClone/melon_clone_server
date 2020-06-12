package com.devgd.melonclone.domain.artist.dao;

import java.util.Optional;

import com.devgd.melonclone.domain.artist.domain.ArtistEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistEntity, Integer> {
	Optional<ArtistEntity> findByArtistId(Integer artistId);
	Optional<ArtistEntity> findByArtistName(String artistName);
}