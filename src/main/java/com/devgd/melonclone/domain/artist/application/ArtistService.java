package com.devgd.melonclone.domain.artist.application;

import java.lang.annotation.Annotation;
import java.util.Optional;

import com.devgd.melonclone.domain.artist.dao.ArtistLikeRepository;
import com.devgd.melonclone.domain.artist.dao.ArtistRepository;
import com.devgd.melonclone.domain.artist.domain.ArtistEntity;
import com.devgd.melonclone.domain.artist.domain.ArtistLikeEntity;
import com.devgd.melonclone.domain.artist.domain.ArtistLikeId;
import com.devgd.melonclone.domain.artist.dto.ArtistDto;
import com.devgd.melonclone.domain.artist.exception.ArtistNotFoundException;
import com.devgd.melonclone.domain.model.BaseEntity;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArtistService {
	private final ArtistRepository artistRepository;
	private final ArtistLikeRepository artistLikeRepository;

	public Long addArtist(ArtistDto artistDto) {
		return artistRepository.save(artistDto.toEntity()).getArtistId();
	}

	public ArtistDto getArtist(Long artistId) {
		final Optional<ArtistEntity> artist = artistRepository.findById(artistId);
		artist.orElseThrow(() -> new ArtistNotFoundException(artistId+""));
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(artist.get(), ArtistDto.class);
	}

	public boolean removeArtist(ArtistDto artistDto) {
		artistRepository.delete(artistDto.toEntity());
		return true;
	}
	
	public boolean removeArtistById(Long artistId) {
		artistRepository.deleteById(artistId);
		return true;
	}

	public void addLike(Long artistId, Long userId) {
		if (artistLikeRepository.findByArlIdArlArtistIdAndArlIdArlUserId(artistId, userId) == null) {
			artistLikeRepository.save(new ArtistLikeEntity(new ArtistLikeId(artistId, userId)));
		}
	}
}