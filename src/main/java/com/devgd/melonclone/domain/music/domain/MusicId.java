package com.devgd.melonclone.domain.music.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Builder;
import lombok.Getter;

@Getter
@Embeddable
public class MusicId implements Serializable {
	private static final long serialVersionUID = -390950220271593086L;

	@Column(nullable = false)
	private final Long music_id;

	@Column(nullable = false)
	private final Long music_artist_id;

	@Column(nullable = false)
	private final Long music_album_id;

	@Column(nullable = false)
	private final Long music_category_id;

	@Builder
	public MusicId(Long music_id, Long music_artist_id, Long music_album_id, Long music_category_id) {
		this.music_id = music_id;
		this.music_artist_id = music_artist_id;
		this.music_album_id = music_album_id;
		this.music_category_id = music_category_id;
	}
}