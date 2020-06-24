package com.devgd.melonclone.domain.playlist.application;

import java.util.ArrayList;
import java.util.List;

import com.devgd.melonclone.domain.music.dao.MusicDao;
import com.devgd.melonclone.domain.music.domain.MusicEntity;
import com.devgd.melonclone.domain.playlist.dao.PlaylistDao;
import com.devgd.melonclone.domain.playlist.domain.PlaylistEntity;
import com.devgd.melonclone.domain.playlist.domain.PlaylistMusicEntity;
import com.devgd.melonclone.domain.playlist.domain.UserPlaylistEntity;
import com.devgd.melonclone.domain.playlist.dto.PlaylistDto;
import com.devgd.melonclone.domain.playlist.dto.PlaylistMusicDto;
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

	// public PlaylistEntity checkAlbumAuth(Integer albumId, Integer userId) {
	// 	// ArtistEntity artistEntity = artistDao.getArtistByUserId(userId);
	// 	// if (artistEntity == null) throw new ArtistNotFoundException("user id "+userId);

	// 	// AlbumEntity albumEntity = albumDao.getAlbumWithArtist(albumId, artistEntity);
	// 	// if (albumEntity == null) throw new AlbumNotFoundException(""+albumId);
	// 	// return albumEntity;
	// }

	// public PlaylistEntity checkAlbumCreateAuth(Integer userId) {
	// 	// ArtistEntity artistEntity = artistDao.getArtistByUserId(userId);
	// 	// if (artistEntity == null) throw new ArtistNotFoundException("user id "+userId);
	// 	// else return artistEntity;
	// }

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
		PlaylistEntity playlistEntity = playlistDao.getUserPlaylistById(userEntity.getUserId(), playlistId);
		MusicEntity musicEntity = musicDao.getMusic(musicId);
		PlaylistMusicEntity playlistMusicEntity = PlaylistMusicEntity.builder()
			.pmPlaylist(playlistEntity)
			.pmMusic(musicEntity)
			.build();
		playlistDao.savePlaylistMusic(playlistMusicEntity);

		return playlistMusicEntity.toDto();
	}
}