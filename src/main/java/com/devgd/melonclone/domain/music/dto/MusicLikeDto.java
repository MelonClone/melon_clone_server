package com.devgd.melonclone.domain.music.dto;

import java.time.LocalDateTime;

import com.devgd.melonclone.domain.model.BaseDto;
import com.devgd.melonclone.domain.music.domain.MusicLikeEntity;
import com.devgd.melonclone.domain.music.domain.MusicLikeId;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MusicLikeDto implements BaseDto<MusicLikeEntity> {
	private String musicId;
	private Integer userId;
	private LocalDateTime likeTime = LocalDateTime.now();

	@Override
	public MusicLikeEntity toEntity(){
		return MusicLikeEntity.builder()
				.mulId(
					MusicLikeId.builder()
					.musicId(musicId)
					.userId(userId)
					.build()
				)
				.likeTime(likeTime)
				.build();
	}

	@Builder
	public MusicLikeDto(String musicId, Integer userId, LocalDateTime likeTime) {
		this.musicId = musicId;
		this.userId = userId;
		this.likeTime = likeTime;
	}
}