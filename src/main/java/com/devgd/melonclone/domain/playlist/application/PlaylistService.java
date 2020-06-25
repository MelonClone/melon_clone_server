package com.devgd.melonclone.domain.playlist.application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.devgd.melonclone.domain.music.dao.MusicDao;
import com.devgd.melonclone.domain.music.domain.MusicEntity;
import com.devgd.melonclone.domain.playlist.dao.PlaylistDao;
import com.devgd.melonclone.domain.playlist.domain.PlaylistEntity;
import com.devgd.melonclone.domain.playlist.domain.PlaylistMusicEntity;
import com.devgd.melonclone.domain.playlist.domain.UserPlaylistEntity;
import com.devgd.melonclone.domain.playlist.dto.PlaylistDto;
import com.devgd.melonclone.domain.playlist.dto.PlaylistMusicDto;
import com.devgd.melonclone.domain.playlist.exception.PlaylistNotFoundException;
import com.devgd.melonclone.domain.user.dao.UserDao;
import com.devgd.melonclone.domain.user.domain.UserEntity;
import com.devgd.melonclone.domain.user.dto.UserDto;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PlaylistService {

	private final PlaylistDao playlistDao;
	private final UserDao userDao;
	private final MusicDao musicDao;

	public PlaylistEntity checkPlaylistAuth(Integer userId, Integer playlistId) {
		PlaylistEntity playlistEntity = playlistDao.getUserPlaylistById(userId, playlistId);
		if (playlistEntity == null) throw new PlaylistNotFoundException("user id "+userId);

		return playlistEntity;
	}

	public Integer addPlaylist(UserDto userDto, PlaylistDto playlistDto) {
		UserEntity userEntity = userDao.findById(userDto.getUserId());
		PlaylistEntity playlistEntity = playlistDto.toEntity();
		playlistDao.savePlaylist(playlistEntity);
		playlistDao.saveUserPlaylist(
			UserPlaylistEntity.builder()
				.upUser(userEntity)
				.upPlaylist(playlistEntity)
				.build());

		return playlistEntity.getPlaylistId();
	}

	public List<PlaylistDto> getUserPlaylists(UserDto userDto) {
		UserEntity userEntity = userDao.findById(userDto.getUserId());
		List<UserPlaylistEntity> upeList = playlistDao.getAllUsersPlaylist(userEntity);

		ArrayList<PlaylistDto> playlists = new ArrayList<>();
		for (UserPlaylistEntity upe : upeList) {
			playlists.add(upe.getUpPlaylist().toDto());
		}
		return playlists;
	}

	public PlaylistMusicDto addPlaylistMusic(UserDto userDto, Integer playlistId, String musicId) {
		UserEntity userEntity = userDao.findById(userDto.getUserId());
		PlaylistEntity playlistEntity = checkPlaylistAuth(userEntity.getUserId(), playlistId);

		Integer lastOrderId = null;
		List<PlaylistMusicEntity> playlistMusicEntityList = playlistDao.getAllPlaylistMusic(playlistEntity);
		if (playlistMusicEntityList.size() > 0) {
			Collections.sort(playlistMusicEntityList);
			lastOrderId = playlistMusicEntityList.get(playlistMusicEntityList.size()-1).getPmId();
		}
		MusicEntity musicEntity = musicDao.getMusic(musicId);
		PlaylistMusicEntity playlistMusicEntity = PlaylistMusicEntity.builder()
			.pmPlaylist(playlistEntity)
			.pmMusic(musicEntity)
			.order(lastOrderId)
			.build();
		playlistDao.savePlaylistMusic(playlistMusicEntity);

		return playlistMusicEntity.toDto();
	}

	public void deletePlaylistMusic(UserDto userDto, Integer playlistId, Integer itemId) {
		UserEntity userEntity = userDao.findById(userDto.getUserId());
		PlaylistEntity playlistEntity = checkPlaylistAuth(userEntity.getUserId(), playlistId);
		
		PlaylistMusicEntity playlistMusicEntity = PlaylistMusicEntity.builder()
			.pmId(itemId)
			.pmPlaylist(playlistEntity)
			.build();
		playlistDao.removePlaylistMusic(playlistMusicEntity);
	}

	public List<PlaylistMusicDto> getPlaylistMusics(UserDto userDto, Integer playlistId) {
		UserEntity userEntity = userDao.findById(userDto.getUserId());
		PlaylistEntity playlistEntity = checkPlaylistAuth(userEntity.getUserId(), playlistId);

		List<PlaylistMusicEntity> playlistMusicEntityList = playlistDao.getAllPlaylistMusic(playlistEntity);
		Collections.sort(playlistMusicEntityList);
		
		ArrayList<PlaylistMusicDto> playlistMusics = new ArrayList<>();
		for (PlaylistMusicEntity pme : playlistMusicEntityList) {
			playlistMusics.add(pme.toDto());
		}

		return playlistMusics;
	}
}