package com.devgd.melonclone.domain.music.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class MusicId implements Serializable {

	@Column(nullable = false)
	private Integer music_id;

	@Column(nullable = false)
	private Integer music_artist_id;

	@Column(nullable = false)
	private Integer music_album_id;

	@Column(nullable = false)
	private Integer music_category_id;

	@Builder
	public MusicId(Integer music_id, Integer music_artist_id, Integer music_album_id, Integer music_category_id) {
		this.music_id = music_id;
		this.music_artist_id = music_artist_id;
		this.music_album_id = music_album_id;
		this.music_category_id = music_category_id;
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof MusicId) && music_id == ((MusicId) o).getMusic_id() && music_artist_id == ((MusicId) o).getMusic_artist_id());
	}

	@Override
	public int hashCode() {
		return (int)(music_id ^ music_artist_id);
	}
}