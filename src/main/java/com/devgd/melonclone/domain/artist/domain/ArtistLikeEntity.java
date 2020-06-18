package com.devgd.melonclone.domain.artist.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
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

	@Column(name = "like_time", nullable = false)
	private LocalDateTime likeTime;

	public ArtistLikeEntity(ArtistLikeId arlId) {
		this.arlId = arlId;
	}

	@Builder
	public ArtistLikeEntity(ArtistLikeId arlId, LocalDateTime likeTime) {
		this.arlId = arlId;
		this.likeTime = likeTime;
	}

	@Override
	public ArtistLikeDto toDto() {
		ModelMapper modelMapper = new ModelMapper();
		ArtistLikeDto userDto = modelMapper.map(this, ArtistLikeDto.class);
		userDto.setArtistId(arlId.getArlArtistId());
		userDto.setUserId(arlId.getArlUserId());
		userDto.setLikeTime(likeTime);

		return userDto;
	}
}