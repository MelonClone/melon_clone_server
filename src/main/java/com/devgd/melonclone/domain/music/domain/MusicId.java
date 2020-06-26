package com.devgd.melonclone.domain.music.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Embeddable
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class MusicId implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6297977040964262297L;

	@EqualsAndHashCode.Include
	@Column(name = "music_id", nullable = false)
	private String musicId;

	@EqualsAndHashCode.Include
	@Column(name = "music_artist_id", nullable = false)
	private Integer musicArtistId;

	@EqualsAndHashCode.Include
	@Column(name = "music_album_id", nullable = false)
	private Integer musicAlbumId;

	@EqualsAndHashCode.Include
	@Column(name = "music_category_id", nullable = false)
	private Integer musicCategoryId;

	@Builder
	public MusicId(String musicId, Integer musicArtistId, Integer musicAlbumId, Integer musicCategoryId) {
		this.musicId = musicId;
		this.musicArtistId = musicArtistId;
		this.musicAlbumId = musicAlbumId;
		this.musicCategoryId = musicCategoryId;
	}

}