package com.devgd.melonclone.domain.artist.dao;

import java.util.Optional;

import com.devgd.melonclone.domain.artist.domain.ArtistEntity;
import com.devgd.melonclone.domain.user.domain.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistEntity, Integer> {
	Optional<ArtistEntity> findByArtistId(Integer artistId);
	Optional<ArtistEntity> findByArtistName(String artistName);
	Optional<ArtistEntity> findByArtistUser(UserEntity userEntity);
	Optional<ArtistEntity> findByArtistIdAndArtistUser(Integer artistId, UserEntity userEntity);
	// Optional<ArtistEntity> findByArtistUserId(Integer userId);
	// Optional<ArtistEntity> findByArtistIdAndArtistUserId(Integer artistId, Integer userId);
}