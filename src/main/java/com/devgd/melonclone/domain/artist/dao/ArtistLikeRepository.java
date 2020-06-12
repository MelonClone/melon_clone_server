package com.devgd.melonclone.domain.artist.dao;

import java.util.List;
import java.util.Optional;

import com.devgd.melonclone.domain.artist.domain.ArtistLikeEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistLikeRepository extends JpaRepository<ArtistLikeEntity, Integer> {
	List<ArtistLikeEntity> findByArlIdArlArtistId(Integer artistId);
	List<ArtistLikeEntity> findByArlIdArlUserId(Integer userId);
	Optional<ArtistLikeEntity> findByArlIdArlArtistIdAndArlIdArlUserId(Integer artistId, Integer userId);

	@Query("SELECT alt "+
		"FROM ArtistLikeEntity alt "+
		"WHERE alt.arlId.arlArtistId = :artist_id "+
		"AND alt.arlId.arlUserId = :user_id")
	Optional<ArtistLikeEntity> findById(
		@Param("artist_id") Integer artistId, 
		@Param("user_id") Integer userId);
}