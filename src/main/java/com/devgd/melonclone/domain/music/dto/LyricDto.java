package com.devgd.melonclone.domain.music.dto;

import com.devgd.melonclone.domain.model.BaseDto;
import com.devgd.melonclone.domain.music.domain.LyricEntity;
import com.devgd.melonclone.global.common.util.ModelMapperUtils;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LyricDto implements BaseDto<LyricEntity> {
	
	private Integer lyricId;
	private String lyricMusicId;
	private Integer lyricTime;
	private String lyricTxt;
	
	@Builder
	public LyricDto(Integer lyricId, String lyricMusicId, 
		Integer lyricTime, String lyricTxt) {
		this.lyricId = lyricId;
		this.lyricMusicId = lyricMusicId;
		this.lyricTime = lyricTime;
		this.lyricTxt = lyricTxt;
	}
	
	@Override
	public LyricEntity toEntity() {
		return LyricEntity.builder()
			.lyricId(lyricId)
			.lyricMusicId(lyricMusicId)
			.lyricTime(lyricTime)
			.lyricTxt(lyricTxt)
			.build();
	}

	@Override
	public LyricDto parse(LyricEntity lyricEntity) {
		ModelMapperUtils.getModelMapper().map(lyricEntity, this);
		return this;
	}
}