package com.devgd.melonclone.domain.artist.dto;

import com.devgd.melonclone.domain.artist.domain.ArtistLikeEntity;
import com.devgd.melonclone.domain.artist.domain.ArtistLikeId;
import com.devgd.melonclone.domain.model.BaseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ArtistLikeDto implements BaseDto<ArtistLikeEntity> {
	private Integer artistId;
	private Integer userId;

	@Override
	public ArtistLikeEntity toEntity(){
		return ArtistLikeEntity.builder()
				.arlId(
					ArtistLikeId.builder()
					.artistId(artistId)
					.userId(userId)
					.build()
				)
				.build();
	}

	@Builder
	public ArtistLikeDto(Integer artistId, Integer userId) {
		this.artistId = artistId;
		this.userId = userId;
	}
}