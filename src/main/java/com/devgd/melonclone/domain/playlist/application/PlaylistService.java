package com.devgd.melonclone.domain.playlist.application;

import com.devgd.melonclone.domain.album.domain.AlbumEntity;
import com.devgd.melonclone.domain.artist.domain.ArtistEntity;
import com.devgd.melonclone.domain.playlist.dao.PlaylistDao;
import com.devgd.melonclone.domain.playlist.domain.PlaylistEntity;
import com.devgd.melonclone.domain.playlist.domain.UserPlaylistEntity;
import com.devgd.melonclone.domain.playlist.dto.PlaylistDto;
import com.devgd.melonclone.domain.user.dto.UserDto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PlaylistService {

	private final PlaylistDao playlistDao;

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
		PlaylistEntity playlisEntity = playlistDto.toEntity();
		// checkAlbumCreateAuth(userDto.getUserId());
		PlaylistEntity playlistEntity = playlistDao.savePlaylist(playlisEntity);
		playlistDao.saveUserPlaylist(
			UserPlaylistEntity.builder()
			.upUser(userDto.toEntity())
			.upPlaylist(playlistEntity)
			.build());

		return playlistEntity.getPlaylistId();
	}
}