package com.devgd.melonclone.domain.music.dto;

import java.time.LocalDateTime;

import com.devgd.melonclone.domain.model.BaseDto;
import com.devgd.melonclone.domain.music.domain.MusicEntity;
import com.devgd.melonclone.domain.music.domain.MusicId;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MusicDto implements BaseDto<MusicEntity> {
	
	private Integer musicId;
	private Integer musicArtistId;
	private Integer musicAlbumId;
	private Integer musicCategoryId;
	private String musicName;
	private Integer musicLike = 0;
	private Integer musicPlaytime = 0;
	private LocalDateTime createDate = LocalDateTime.now();

	@Override
	public MusicEntity toEntity() {
		return MusicEntity.builder()
			.music(MusicId.builder()
				.musicId(musicId)
				.musicArtistId(musicArtistId)
				.musicAlbumId(musicAlbumId)
				.musicCategoryId(musicCategoryId)
				.build())
			.musicName(musicName)
			.musicLike(musicLike)
			.musicPlaytime(musicPlaytime)
			.createDate(createDate)
			.build();
	}
	
	@Builder
	public MusicDto(Integer musicId, Integer musicArtistId, Integer musicAlbumId, 
		Integer musicCategoryId, String musicName, Integer musicLike, 
		Integer musicPlaytime, LocalDateTime createDate) {
		this.musicId = musicId;
		this.musicArtistId = musicArtistId;
		this.musicAlbumId = musicAlbumId;
		this.musicCategoryId = musicCategoryId;
		this.musicName = musicName;
		this.musicLike = musicLike;
		this.musicPlaytime = musicPlaytime;
		this.createDate = createDate;
	}
}