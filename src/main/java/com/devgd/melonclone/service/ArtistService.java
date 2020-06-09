package com.devgd.melonclone.service;

import com.devgd.melonclone.dto.ArtistDto;
import com.devgd.melonclone.repository.ArtistRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArtistService {
	private ArtistRepository artistRepository;

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
}