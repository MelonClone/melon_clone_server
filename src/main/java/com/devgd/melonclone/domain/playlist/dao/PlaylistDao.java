package com.devgd.melonclone.domain.playlist.dao;

import com.devgd.melonclone.domain.playlist.domain.PlaylistEntity;
import com.devgd.melonclone.domain.playlist.domain.UserPlaylistEntity;

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
		return playlistRepository.saveAndFlush(playlistEntity);
	}

	public UserPlaylistEntity saveUserPlaylist(UserPlaylistEntity userPlaylistEntity) {
		return userPlaylistRepository.save(userPlaylistEntity);
	}
}