package com.devgd.melonclone.domain.music.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class MusicId implements Serializable {

	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "music_id", nullable = false)
	private Integer musicId;

	@Column(name = "music_artist_id", nullable = false)
	private Integer musicArtistId;

	@Column(name = "music_album_id", nullable = false)
	private Integer musicAlbumId;

	@Column(name = "music_category_id", nullable = false)
	private Integer musicCategoryId;

	@Builder
	public MusicId(Integer musicId, Integer musicArtistId, Integer musicAlbumId, Integer musicCategoryId) {
		this.musicId = musicId;
		this.musicArtistId = musicArtistId;
		this.musicAlbumId = musicAlbumId;
		this.musicCategoryId = musicCategoryId;
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof MusicId) 
		&& musicId == ((MusicId) o).getMusicId() 
		&& musicArtistId == ((MusicId) o).getMusicArtistId()
		&& musicAlbumId == ((MusicId) o).getMusicAlbumId()
		&& musicCategoryId == ((MusicId) o).getMusicCategoryId());
	}

	@Override
	public int hashCode() {
		return (int)(musicId ^ musicArtistId ^ musicAlbumId ^ musicCategoryId);
	}
}