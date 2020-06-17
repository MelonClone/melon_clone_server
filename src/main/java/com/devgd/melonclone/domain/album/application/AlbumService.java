package com.devgd.melonclone.domain.album.application;

import com.devgd.melonclone.domain.album.dao.AlbumDao;
import com.devgd.melonclone.domain.album.domain.AlbumEntity;
import com.devgd.melonclone.domain.album.dto.AlbumDto;
import com.devgd.melonclone.domain.artist.dao.ArtistDao;
import com.devgd.melonclone.domain.artist.domain.ArtistEntity;
import com.devgd.melonclone.domain.artist.exception.ArtistNotFoundException;
import com.devgd.melonclone.domain.user.dto.UserDto;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AlbumService {

	private final AlbumDao albumDao;
	private final ArtistDao artistDao;

	public boolean checkAlbumAuth(Integer albumId, Integer artistId, Integer userId) {
		ArtistEntity artistEntity = artistDao.getArtistWithUserId(artistId, userId);
		if (artistEntity == null) return false;
		if (albumDao.getAlbumWithArtist(albumId, artistEntity) == null) return false;
		return true;
	}

	public Integer addAlbum(UserDto userDto, AlbumDto albumDto) {
		ArtistEntity artistEntity = artistDao.getArtistByUserId(userDto.getUserId());
		if (artistEntity == null) throw new ArtistNotFoundException("user id "+userDto.getUserId());
		AlbumEntity albumEntity = albumDto.toEntity();
		albumEntity.setAlbumArtist(artistEntity);
		return albumDao.save(albumEntity);
	}
}