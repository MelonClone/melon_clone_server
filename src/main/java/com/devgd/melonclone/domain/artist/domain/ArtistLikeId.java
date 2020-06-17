package com.devgd.melonclone.domain.artist.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
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

	@Override
	public boolean equals(Object o) {
		return ((o instanceof ArtistLikeId) && arlArtistId == ((ArtistLikeId) o).getArlArtistId() && arlUserId == ((ArtistLikeId) o).getArlUserId());
	}

	@Override
	public int hashCode() {
		return (int)(arlArtistId ^ arlUserId);
	}
}