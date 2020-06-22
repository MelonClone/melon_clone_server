package com.devgd.melonclone.domain.music.application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.devgd.melonclone.domain.album.dao.AlbumDao;
import com.devgd.melonclone.domain.album.domain.AlbumEntity;
import com.devgd.melonclone.domain.album.exception.AlbumNotFoundException;
import com.devgd.melonclone.domain.artist.dao.ArtistDao;
import com.devgd.melonclone.domain.artist.domain.ArtistEntity;
import com.devgd.melonclone.domain.artist.exception.ArtistNotFoundException;
import com.devgd.melonclone.domain.artist.exception.ArtistPermissionException;
import com.devgd.melonclone.domain.music.dao.MusicDao;
import com.devgd.melonclone.domain.music.domain.LyricEntity;
import com.devgd.melonclone.domain.music.domain.MusicEntity;
import com.devgd.melonclone.domain.music.dto.LyricDto;
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

	public boolean checkMusicEditAuth(Integer userId, String musicId) {
		ArtistEntity artistEntity = artistDao.getArtistByUserId(userId);
		if (artistEntity == null) throw new ArtistNotFoundException("user id "+userId);
		MusicEntity musicEntity = musicDao.getMusic(musicId);
		if (musicEntity == null || musicEntity.getMusic().getMusicArtistId() != artistEntity.getArtistId()) return false;
		else return true;
	}

	public ArtistEntity checkMusicCreateAuth(Integer albumId, Integer userId) {
		ArtistEntity artistEntity = artistDao.getArtistByUserId(userId);
		if (artistEntity == null) throw new ArtistNotFoundException("user id "+userId);
		AlbumEntity albumEntity = albumDao.getAlbumWithArtist(albumId, artistEntity);
		if (albumEntity == null) throw new AlbumNotFoundException(""+albumId);
		else return artistEntity;
	}
	
	public String addMusic(MusicDto musicDto, UserDto userDto) {
		ArtistEntity artistEntity = checkMusicCreateAuth(musicDto.getMusicAlbumId(), userDto.getUserId());
		musicDto.setMusicArtistId(artistEntity.getArtistId());
		return musicDao.save(musicDto.toEntity());
	}

	public MusicDto getMusic(String musicId) {
		return musicDao.getMusic(musicId).toDto();
	}

	public List<MusicDto> getMusicsByArtistId(Integer artistId) {
		List<MusicEntity> musicEntityList = musicDao.getMusicsByArtistId(artistId);
		List<MusicDto> musicDtoList = new ArrayList<>();
		for (int i=0; i<musicEntityList.size(); i++) {
			MusicEntity musicEntity = musicEntityList.get(i);
			musicDtoList.add(musicEntity.toDto());
		}

		return musicDtoList;
	}

	public List<MusicDto> getMusicsByAlbumId(Integer albumId) {
		List<MusicEntity> musicEntityList = musicDao.getMusicsByAlbumId(albumId);
		List<MusicDto> musicDtoList = new ArrayList<>();
		for (int i=0; i<musicEntityList.size(); i++) {
			MusicEntity musicEntity = musicEntityList.get(i);
			musicDtoList.add(musicEntity.toDto());
		}

		return musicDtoList;
	}

	public boolean removeMusic(String musicId, UserDto userDto) {
		if (!checkMusicEditAuth(userDto.getUserId(), musicId)) throw new ArtistPermissionException();
		
		return musicDao.deleteMusic(musicDao.getMusic(musicId));
	}

	public String changeMusic(MusicDto musicDto, UserDto userDto) {
		if (!checkMusicEditAuth(userDto.getUserId(), musicDto.getMusicId())) throw new ArtistPermissionException();
		musicDto.setMusicArtistId(artistDao.getArtistByUserId(userDto.getUserId()).getArtistId());
		return musicDao.save(musicDto.toEntity());
	}

	public List<LyricDto> getLyrics(String musicId) {
		List<LyricEntity> lyricEntityList = musicDao.getLyrics(musicId);
		List<LyricDto> lyricDtoList = new ArrayList<>();
		for (int i=0; i<lyricEntityList.size(); i++) {
			LyricEntity lyricEntity = lyricEntityList.get(i);
			lyricDtoList.add(lyricEntity.toDto());
		}

		return lyricDtoList;
	}

	public void changeLyric(String musicId, List<LyricDto> lyricDtoList) {
		List<LyricEntity> lyricEntityList = new ArrayList<>();
		lyricDtoList.sort(new Comparator<LyricDto>() {
			@Override
			public int compare(LyricDto o1, LyricDto o2) {
				return o1.getLyricTime() < o2.getLyricTime() ? -1 : o1.getLyricTime() == o2.getLyricTime() ? 0 : 1;
			}
		});
		for (int i=0; i<lyricDtoList.size(); i++) {
			LyricDto lyricDto = lyricDtoList.get(i);
			lyricEntityList.add(LyricEntity.builder()
				.lyricId(i+1)
				.lyricMusicId(musicId)
				.lyricTime(lyricDto.getLyricTime())
				.lyricTxt(lyricDto.getLyricTxt())
				.build());
		}
		musicDao.changeLyrics(lyricEntityList);
	}
}