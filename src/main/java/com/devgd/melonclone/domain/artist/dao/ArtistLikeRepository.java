package com.devgd.melonclone.domain.artist.dao;

import java.util.List;
import java.util.Optional;

import com.devgd.melonclone.domain.artist.domain.ArtistLikeEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistLikeRepository extends JpaRepository<ArtistLikeEntity, Long> {
	List<ArtistLikeEntity> findByArlIdArlArtistId(Long artistId);
	List<ArtistLikeEntity> findByArlIdArlUserId(Long userId);
	Optional<ArtistLikeEntity> findByArlIdArlArtistIdAndArlIdArlUserId(Long artistId, Long userId);

	@Query("SELECT alt "+
		"FROM ArtistLikeEntity alt "+
		"WHERE alt.arlId.arlArtistId = :artist_id "+
		"AND alt.arlId.arlUserId = :user_id")
	Optional<ArtistLikeEntity> findById(
		@Param("artist_id") Long artistId, 
		@Param("user_id") Long userId);
}