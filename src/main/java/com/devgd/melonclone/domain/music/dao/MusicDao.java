package com.devgd.melonclone.domain.music.dao;

import com.devgd.melonclone.domain.music.domain.MusicEntity;
import com.devgd.melonclone.domain.music.exception.MusicNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MusicDao {
	private final MusicRepository musicRepository;

	public Integer save(MusicEntity musicEntity) {
		return musicRepository.save(musicEntity).getMusic().getMusicId();
	}

	public MusicEntity getMusic(Integer musicId) {
		return musicRepository.findByMusicMusicId(musicId)
			.orElseThrow(() -> new MusicNotFoundException(musicId+""));
	}
}