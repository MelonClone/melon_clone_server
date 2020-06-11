package com.devgd.melonclone.domain.artist.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.devgd.melonclone.domain.model.BaseEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "artist_like_table")
public class ArtistLikeEntity extends BaseEntity implements Serializable {

	@EmbeddedId
	private ArtistLikeId arlId;

	@Builder
	public ArtistLikeEntity(ArtistLikeId arlId) {
		this.arlId = arlId;
	}
}