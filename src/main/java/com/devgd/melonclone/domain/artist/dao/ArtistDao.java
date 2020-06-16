package com.devgd.melonclone.domain.artist.dao;

import java.util.Optional;

import com.devgd.melonclone.domain.artist.domain.ArtistEntity;
import com.devgd.melonclone.domain.artist.domain.ArtistLikeEntity;
import com.devgd.melonclone.domain.artist.domain.ArtistLikeId;
import com.devgd.melonclone.domain.artist.dto.ArtistDto;
import com.devgd.melonclone.domain.artist.exception.ArtistNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ArtistDao {
	private final ArtistRepository artistRepository;
	private final ArtistLikeRepository artistLikeRepository;

	public Integer save(ArtistDto artistDto) {
		return artistRepository.save(artistDto.toEntity()).getArtistId();
	}

	public ArtistEntity getArtist(Integer artistId) {
		final Optional<ArtistEntity> artist = artistRepository.findById(artistId);
		artist.orElseThrow(() -> new ArtistNotFoundException(artistId+""));
		return artist.get();
	}

	public boolean removeArtist(ArtistDto artistDto) {
		artistRepository.delete(artistDto.toEntity());
		return true;
	}

	public boolean removeArtistById(Integer artistId) {
		artistRepository.deleteById(artistId);
		return true;
	}

	public void addLike(Integer artistId, Integer userId) {
		if (artistLikeRepository.findByArlIdArlArtistIdAndArlIdArlUserId(artistId, userId) == null) {
			artistLikeRepository.save(new ArtistLikeEntity(new ArtistLikeId(artistId, userId)));
		}
	}
}