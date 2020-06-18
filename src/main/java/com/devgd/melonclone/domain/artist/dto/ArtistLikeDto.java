package com.devgd.melonclone.domain.artist.dto;

import java.time.LocalDateTime;

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
	private LocalDateTime likeTime = LocalDateTime.now();

	@Override
	public ArtistLikeEntity toEntity(){
		return ArtistLikeEntity.builder()
				.arlId(
					ArtistLikeId.builder()
					.artistId(artistId)
					.userId(userId)
					.build()
				)
				.likeTime(likeTime)
				.build();
	}

	@Builder
	public ArtistLikeDto(Integer artistId, Integer userId, LocalDateTime likeTime) {
		this.artistId = artistId;
		this.userId = userId;
		this.likeTime = likeTime;
	}
}