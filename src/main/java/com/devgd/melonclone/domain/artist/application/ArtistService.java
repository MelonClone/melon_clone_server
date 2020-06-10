package com.devgd.melonclone.domain.artist.application;

import com.devgd.melonclone.domain.artist.dao.ArtistLikeRepository;
import com.devgd.melonclone.domain.artist.dao.ArtistRepository;
import com.devgd.melonclone.domain.artist.domain.ArtistLikeEntity;
import com.devgd.melonclone.domain.artist.domain.ArtistLikeId;
import com.devgd.melonclone.domain.artist.dto.ArtistDto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArtistService {
	private ArtistRepository artistRepository;
	private ArtistLikeRepository artistLikeRepository;

	public Long addArtist(ArtistDto artistDto) {
		return artistRepository.save(artistDto.toEntity()).getArtist_id();
	}

	public ArtistDto getArtist(Long artistId) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(artistRepository.findById(artistId), ArtistDto.class);
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
		if (artistLikeRepository.findOne(artistId, userId).isPresent()) {
			artistLikeRepository.save(new ArtistLikeEntity(new ArtistLikeId(artistId, userId)));
		}
	}
}