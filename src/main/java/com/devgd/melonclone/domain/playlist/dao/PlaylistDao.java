package com.devgd.melonclone.domain.playlist.dao;

import java.util.List;

import com.devgd.melonclone.domain.playlist.domain.PlaylistEntity;
import com.devgd.melonclone.domain.playlist.domain.UserPlaylistEntity;
import com.devgd.melonclone.domain.user.domain.UserEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaylistDao {
	private final PlaylistRepository playlistRepository;
	private final UserPlaylistRepository userPlaylistRepository;

	public PlaylistEntity savePlaylist(PlaylistEntity playlistEntity) {
		// Generated values are only gurateed to be generated at flush time.
		return playlistRepository.save(playlistEntity);
	}

	public UserPlaylistEntity saveUserPlaylist(UserPlaylistEntity userPlaylistEntity) {
		return userPlaylistRepository.save(userPlaylistEntity);
	}

	public List<UserPlaylistEntity> getAllUsersPlaylist(UserEntity userEntity) {
		return userPlaylistRepository.findAllByUpUserUserId(userEntity.getUserId());
	}
}