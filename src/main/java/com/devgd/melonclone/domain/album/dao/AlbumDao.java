package com.devgd.melonclone.domain.album.dao;

import java.util.List;

import com.devgd.melonclone.domain.album.domain.AlbumEntity;
import com.devgd.melonclone.domain.album.exception.AlbumNotFoundException;
import com.devgd.melonclone.domain.artist.domain.ArtistEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AlbumDao {
	private final AlbumRepository albumRepository;

	public Integer save(AlbumEntity albumEntity) {
		return albumRepository.save(albumEntity).getAlbumId();
	}

	public AlbumEntity getAlbum(Integer albumId) {
		return albumRepository.findByAlbumId(albumId)
			.orElseThrow(() -> new AlbumNotFoundException(albumId+""));
	}

	public List<AlbumEntity> getAlbumByArtistId(ArtistEntity artistEntity) {
		return albumRepository.findAllByAlbumArtist(artistEntity);
	}

	public AlbumEntity getAlbumWithArtist(Integer albumId, ArtistEntity artistEntity) {
		return albumRepository.findByAlbumIdAndAlbumArtist(albumId, artistEntity)
			.orElseThrow(() -> new AlbumNotFoundException(albumId+""));
	}
}