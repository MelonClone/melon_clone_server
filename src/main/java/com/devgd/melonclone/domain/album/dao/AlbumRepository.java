package com.devgd.melonclone.domain.album.dao;

import java.util.List;
import java.util.Optional;

import com.devgd.melonclone.domain.album.domain.AlbumEntity;
import com.devgd.melonclone.domain.artist.domain.ArtistEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<AlbumEntity, Integer> {
	Optional<AlbumEntity> findByAlbumId(Integer albumId);
	List<AlbumEntity> findAllByAlbumArtist(ArtistEntity artistEntity);
	List<AlbumEntity> findAllByAlbumArtistArtistId(Integer artistId);
	Optional<AlbumEntity> findByAlbumIdAndAlbumArtist(Integer albumId, ArtistEntity artistEntity);
}