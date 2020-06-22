package com.devgd.melonclone.domain.artist.domain;

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
public class ArtistLikeId implements Serializable {
	
	@Column(name = "arl_artist_id", nullable = false)
	private Integer arlArtistId;
	
	@Column(name = "arl_user_id", nullable = false)
	private Integer arlUserId;

	@Builder
	public ArtistLikeId(Integer artistId, Integer userId) {
		this.arlArtistId = artistId;
		this.arlUserId = userId;
	}
}