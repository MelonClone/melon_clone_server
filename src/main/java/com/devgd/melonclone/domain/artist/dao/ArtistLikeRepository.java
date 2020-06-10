package com.devgd.melonclone.domain.artist.dao;

import java.util.List;
import java.util.Optional;

import com.devgd.melonclone.domain.artist.domain.ArtistLikeEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistLikeRepository extends JpaRepository<ArtistLikeEntity, Long> {
	List<ArtistLikeEntity> findByArtistId(Long artistId);
	List<ArtistLikeEntity> findByUserId(Long userId);
	Optional<ArtistLikeEntity> findOne(Long artistId, Long userId);
}