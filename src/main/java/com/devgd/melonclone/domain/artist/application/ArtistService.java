package com.devgd.melonclone.domain.artist.application;

import com.devgd.melonclone.domain.artist.dao.ArtistDao;
import com.devgd.melonclone.domain.artist.dto.ArtistDto;
import com.devgd.melonclone.domain.artist.exception.ArtistPermissionException;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArtistService {

	private final ArtistDao artistDao;

	public Integer addArtist(ArtistDto artistDto) {
		return artistDao.save(artistDto);
	}

	public ArtistDto getArtist(Integer artistId) {
		return artistDao.getArtist(artistId).toDto();
	}

	public boolean updateArtist(Integer userId, ArtistDto artistDto) {
		if (userId != artistDto.getArtistId()) throw new ArtistPermissionException();
		return artistDao.updateArtist(artistDto);
	}

	public boolean removeArtist(ArtistDto artistDto) {
		return artistDao.removeArtist(artistDto);
	}
	
	public boolean removeArtistById(Integer artistId) {
		return artistDao.removeArtistById(artistId);
	}

	public void addLike(Integer artistId, Integer userId) {
		artistDao.addLike(artistId, userId);
	}
}