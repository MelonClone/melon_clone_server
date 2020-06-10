package com.devgd.melonclone.domain.artist.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Builder;
import lombok.Getter;

@Getter
@Embeddable
public class ArtistLikeId implements Serializable {
	
	@Column(nullable = false)
	private Long arl_artist_id;
	
	@Column(nullable = false)
	private Long arl_user_id;

	@Builder
	public ArtistLikeId(Long artist_id, Long user_id) {
		this.arl_artist_id = artist_id;
		this.arl_user_id = user_id;
	}
}