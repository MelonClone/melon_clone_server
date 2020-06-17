package com.devgd.melonclone.domain.artist.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.devgd.melonclone.domain.artist.dto.ArtistLikeDto;
import com.devgd.melonclone.domain.model.BaseEntity;

import org.modelmapper.ModelMapper;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "artist_like_table")
@NoArgsConstructor
public class ArtistLikeEntity implements Serializable, BaseEntity<ArtistLikeDto> {

	@EmbeddedId
	private ArtistLikeId arlId;

	@Builder
	public ArtistLikeEntity(ArtistLikeId arlId) {
		this.arlId = arlId;
	}

	@Override
	public ArtistLikeDto toDto() {
		ModelMapper modelMapper = new ModelMapper();
		ArtistLikeDto userDto = modelMapper.map(this, ArtistLikeDto.class);
		userDto.setArtistId(arlId.getArlArtistId());
		userDto.setUserId(arlId.getArlUserId());

		return userDto;
	}
}