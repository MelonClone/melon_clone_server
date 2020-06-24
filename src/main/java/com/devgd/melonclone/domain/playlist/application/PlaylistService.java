package com.devgd.melonclone.domain.playlist.application;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import com.devgd.melonclone.domain.playlist.dao.PlaylistDao;
import com.devgd.melonclone.domain.playlist.domain.PlaylistEntity;
import com.devgd.melonclone.domain.playlist.domain.UserPlaylistEntity;
import com.devgd.melonclone.domain.playlist.dto.PlaylistDto;
import com.devgd.melonclone.domain.user.dao.UserDao;
import com.devgd.melonclone.domain.user.domain.UserEntity;
import com.devgd.melonclone.domain.user.dto.UserDto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PlaylistService {

	private final PlaylistDao playlistDao;
	private final UserDao userDao;

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
}