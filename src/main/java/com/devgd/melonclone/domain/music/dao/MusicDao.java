package com.devgd.melonclone.domain.music.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.devgd.melonclone.domain.music.domain.LyricEntity;
import com.devgd.melonclone.domain.music.domain.MusicEntity;
import com.devgd.melonclone.domain.music.domain.MusicId;
import com.devgd.melonclone.domain.music.domain.MusicLikeEntity;
import com.devgd.melonclone.domain.music.domain.MusicLikeId;
import com.devgd.melonclone.domain.music.exception.MusicNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MusicDao {
	private final MusicRepository musicRepository;
	private final MusicLikeRepository musicLikeRepository;
	private final LyricRepository lyricRepository;

	public String save(MusicEntity musicEntity) {
		return musicRepository.save(musicEntity).getMusicId();
	}

	public MusicEntity getMusic(String musicId) {
		return musicRepository.findByMusicId(musicId)
			.orElseThrow(() -> new MusicNotFoundException(musicId+""));
	}

	public List<MusicEntity> getMusicsByArtistId(Integer artistId) {
		return musicRepository.findAllByMusicArtistArtistId(artistId);
	}

	public List<MusicEntity> getMusicsByAlbumId(Integer albumId) {
		return musicRepository.findAllByMusicAlbumAlbumId(albumId);
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
		lyricRepository.save(lyric);
	}

	
	public boolean isLike(String musicId, Integer userId) {
		return !musicLikeRepository.findByMulIdMulMusicIdAndMulIdMulUserId(musicId, userId).isPresent();
	}

	public void addLike(String musicId, Integer userId) {
		musicLikeRepository.save(new MusicLikeEntity(new MusicLikeId(musicId, userId), LocalDateTime.now()));
	}

	public void removeLike(String musicId, Integer userId) {
		musicLikeRepository.delete(new MusicLikeEntity(new MusicLikeId(musicId, userId)));
	}
}