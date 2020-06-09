package com.devgd.melonclone.repository;

import java.util.Optional;

import com.devgd.melonclone.domain.entity.ArtistEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {
	Optional<ArtistEntity> findByName(String artistName);
}