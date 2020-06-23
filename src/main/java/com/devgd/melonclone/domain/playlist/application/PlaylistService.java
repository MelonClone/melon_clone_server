package com.devgd.melonclone.domain.playlist.application;

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

	@Transactional
	public Integer addPlaylist(UserDto userDto, PlaylistDto playlistDto) {
		UserEntity userEntity = userDao.findById(userDto.getUserId());
		PlaylistEntity playlistEntity = playlistDto.toEntity();
		playlistEntity.setPlaylistId(1);
		// checkAlbumCreateAuth(userDto.getUserId());
		PlaylistEntity insertedPlaylistEntity = playlistDao.savePlaylist(playlistEntity);
		System.out.println(userEntity.getUserId());
		UserPlaylistEntity upe = UserPlaylistEntity.builder()
		.upId(1)
			.upUser(userEntity)
			.upPlaylist(insertedPlaylistEntity)
			.build();
		System.out.println(upe.getUpId());
		System.out.println(upe.getUpUser().getUserId());
		System.out.println(upe.getUpPlaylist().getPlaylistId());
		playlistDao.saveUserPlaylist(upe);

		return playlistEntity.getPlaylistId();
	}
}