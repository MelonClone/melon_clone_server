package com.devgd.melonclone.domain.artist.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.devgd.melonclone.domain.model.BaseEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Embeddable
public class ArtistLikeId extends BaseEntity implements Serializable {
	
	@Column(name = "arl_artist_id", nullable = false)
	private Long arlArtistId;
	
	@Column(name = "arl_user_id", nullable = false)
	private Long arlUserId;

	@Builder
	public ArtistLikeId(Long artistId, Long userId) {
		this.arlArtistId = artistId;
		this.arlUserId = userId;
	}
}