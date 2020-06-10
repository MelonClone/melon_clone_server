package com.devgd.melonclone.domain.artist.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "artist_like_table")
public class ArtistLikeEntity {
	
	@EmbeddedId
	private ArtistLikeId arl_id;

	@Builder
	public ArtistLikeEntity(ArtistLikeId arl_id) {
		this.arl_id = arl_id;
	}
}