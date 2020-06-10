package com.devgd.melonclone.domain.artist.dao;

import java.util.Optional;

import com.devgd.melonclone.domain.artist.domain.ArtistEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {
	Optional<ArtistEntity> findById(Long artistId);
	Optional<ArtistEntity> findByName(String artistName);
}