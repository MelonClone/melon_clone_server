package com.devgd.melonclone.domain.playlist.dao;

import java.util.List;

import com.devgd.melonclone.domain.playlist.domain.PlaylistEntity;
import com.devgd.melonclone.domain.playlist.domain.PlaylistMusicEntity;
import com.devgd.melonclone.domain.playlist.domain.UserPlaylistEntity;
import com.devgd.melonclone.domain.playlist.exception.PlaylistNotFoundException;
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
	private final PlaylistMusicRepository playlistMusicRepository;

	public PlaylistEntity savePlaylist(PlaylistEntity playlistEntity) {
		// Generated values are only gurateed to be generated at flush time.
		return playlistRepository.save(playlistEntity);
	}

	public UserPlaylistEntity saveUserPlaylist(UserPlaylistEntity userPlaylistEntity) {
		return userPlaylistRepository.save(userPlaylistEntity);
	}

	public PlaylistEntity getPlaylist(UserEntity userEntity, PlaylistEntity playlistEntity) {
		return userPlaylistRepository.findByUpUserUserIdAndUpPlaylistPlaylistId(userEntity.getUserId(), playlistEntity.getPlaylistId())
			.orElseThrow(() -> new PlaylistNotFoundException(playlistEntity.getPlaylistId()+"")).getUpPlaylist();
	}

	public PlaylistEntity getPlaylistById(Integer playlistId) {
		return playlistRepository.findByPlaylistId(playlistId)
			.orElseThrow(() -> new PlaylistNotFoundException(playlistId+""));
	}

	public PlaylistEntity getUserPlaylistById(Integer userId, Integer playlistId) {
		return userPlaylistRepository.findByUpUserUserIdAndUpPlaylistPlaylistId(userId, playlistId)
			.orElseThrow(() -> new PlaylistNotFoundException(playlistId+"")).getUpPlaylist();
	}

	public List<UserPlaylistEntity> getAllUsersPlaylist(UserEntity userEntity) {
		return userPlaylistRepository.findAllByUpUserUserId(userEntity.getUserId());
	}

	public PlaylistMusicEntity savePlaylistMusic(PlaylistMusicEntity playlistMusicEntity) {
		return playlistMusicRepository.save(playlistMusicEntity);
	}

	public List<PlaylistMusicEntity> getAllPlaylistMusic(PlaylistEntity playlistEntity) {
		return playlistMusicRepository.findAllByPmPlaylistPlaylistId(playlistEntity.getPlaylistId());
	}

	public void removePlaylistMusic(PlaylistMusicEntity playlistMusicEntity) {
		playlistMusicRepository.delete(playlistMusicEntity);
	}

	public PlaylistMusicEntity getBeforeMusic(PlaylistMusicEntity currentItem) {
		return playlistMusicRepository.findById(currentItem.getPmOrder())
			.orElseThrow(() -> new PlaylistNotFoundException(currentItem.getPmPlaylist().getPlaylistId()+""));
	}
}