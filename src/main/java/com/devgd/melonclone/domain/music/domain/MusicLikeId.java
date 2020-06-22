package com.devgd.melonclone.domain.music.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class MusicLikeId implements Serializable {
	
	@Column(name = "mul_music_id", nullable = false)
	private String mulMusicId;
	
	@Column(name = "mul_user_id", nullable = false)
	private Integer mulUserId;

	@Builder
	public MusicLikeId(String musicId, Integer userId) {
		this.mulMusicId = musicId;
		this.mulUserId = userId;
	}
}