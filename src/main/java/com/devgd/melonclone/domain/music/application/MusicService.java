package com.devgd.melonclone.domain.music.application;

import com.devgd.melonclone.domain.album.dao.AlbumDao;
import com.devgd.melonclone.domain.album.domain.AlbumEntity;
import com.devgd.melonclone.domain.album.exception.AlbumNotFoundException;
import com.devgd.melonclone.domain.artist.dao.ArtistDao;
import com.devgd.melonclone.domain.artist.domain.ArtistEntity;
import com.devgd.melonclone.domain.artist.exception.ArtistNotFoundException;
import com.devgd.melonclone.domain.music.dao.MusicDao;
import com.devgd.melonclone.domain.music.dto.MusicDto;
import com.devgd.melonclone.domain.user.dto.UserDto;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MusicService {

	private final MusicDao musicDao;
	private final AlbumDao albumDao;
	private final ArtistDao artistDao;

	public ArtistEntity checkMusicCreateAuth(Integer albumId, Integer userId) {
		ArtistEntity artistEntity = artistDao.getArtistByUserId(userId);
		if (artistEntity == null) throw new ArtistNotFoundException("user id "+userId);
		AlbumEntity albumEntity = albumDao.getAlbumWithArtist(albumId, artistEntity);
		if (albumEntity == null) throw new AlbumNotFoundException(""+albumId);
		else return artistEntity;
	}
	
	public Integer addMusic(MusicDto musicDto, UserDto userDto) {
		ArtistEntity artistEntity = checkMusicCreateAuth(musicDto.getMusicAlbumId(), userDto.getUserId());
		musicDto.setMusicArtistId(artistEntity.getArtistId());
		return musicDao.save(musicDto.toEntity());
	}

	public MusicDto getMusic(Integer musicId) {
		return musicDao.getMusic(musicId).toDto();
	}
}