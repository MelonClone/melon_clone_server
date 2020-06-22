package com.devgd.melonclone.domain.music.dao;

import java.util.List;

import com.devgd.melonclone.domain.music.domain.LyricEntity;
import com.devgd.melonclone.domain.music.domain.MusicEntity;
import com.devgd.melonclone.domain.music.domain.MusicId;
import com.devgd.melonclone.domain.music.exception.MusicNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MusicDao {
	private final MusicRepository musicRepository;
	private final LyricRepository lyricRepository;

	public String save(MusicEntity musicEntity) {
		return musicRepository.save(musicEntity).getMusic().getMusicId();
	}

	public MusicEntity getMusic(String musicId) {
		return musicRepository.findByMusicMusicId(musicId)
			.orElseThrow(() -> new MusicNotFoundException(musicId+""));
	}

	public List<MusicEntity> getMusicsByArtistId(Integer artistId) {
		return musicRepository.findAllByMusicMusicArtistId(artistId);
	}

	public List<MusicEntity> getMusicsByAlbumId(Integer albumId) {
		return musicRepository.findAllByMusicMusicAlbumId(albumId);
	}

	public boolean deleteMusic(MusicEntity musicEntity) {
		musicRepository.delete(musicEntity);
		return true;
	}

	public boolean deleteMusic(MusicId musicId) {
		musicRepository.deleteById(musicId);
		return true;
	}

	public List<LyricEntity> getLyrics(String musicId) {
		return lyricRepository.findAllByLyricMusicId(musicId);
	}

	public void changeLyrics(List<LyricEntity> lyrics) {
		lyricRepository.saveAll(lyrics);
	}

	public void changeLyric(LyricEntity lyric) {

	}
}