package com.devgd.melonclone.domain.music.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.devgd.melonclone.domain.model.BaseEntity;
import com.devgd.melonclone.domain.music.dto.LyricDto;

import org.modelmapper.ModelMapper;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "lyric_table")
@NoArgsConstructor
@IdClass(LyricId.class)
public class LyricEntity implements Serializable, BaseEntity<LyricDto> {
	
	@Id
	@Column(name = "lyric_id")
	private Integer lyricId;

	@Id
	@Column(name = "lyric_music_id")
	private String lyricMusicId;
	
	@Column(name = "lyric_time")
	private Integer lyricTime;
	
	@Column(name = "lyric_txt")
	private String lyricTxt;
	
	@Builder
	public LyricEntity(Integer lyricId, String lyricMusicId, 
		Integer lyricTime, String lyricTxt) {
		this.lyricId = lyricId;
		this.lyricMusicId = lyricMusicId;
		this.lyricTime = lyricTime;
		this.lyricTxt = lyricTxt;
	}

	@Override
	public LyricDto toDto() {
		ModelMapper modelMapper = new ModelMapper();
		LyricDto lyricDto = modelMapper.map(this, LyricDto.class);
		return lyricDto;
	}
}