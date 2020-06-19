package com.devgd.melonclone.domain.music.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.devgd.melonclone.domain.model.BaseEntity;
import com.devgd.melonclone.domain.music.dto.MusicDto;

import org.modelmapper.ModelMapper;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@Table(name = "music_table")
@NoArgsConstructor
@ToString
public class MusicEntity implements Serializable, BaseEntity<MusicDto> {
	@EmbeddedId
	private MusicId music;

	@Column(name = "music_name", length = 45, nullable = false)
	private String musicName;

	@Column(name = "music_like", nullable = false)
	private Integer musicLike;

	@Column(name = "music_playtime", nullable = false)
	private Integer musicPlaytime;

	@Column(name = "create_date", nullable = false)
	private LocalDateTime createDate;

	@Builder
	public MusicEntity(MusicId music, 
			String musicName, Integer musicLike, Integer musicPlaytime, 
			LocalDateTime createDate) {
		this.music = music;
		this.musicName = musicName;
		this.musicLike = musicLike;
		this.musicPlaytime = musicPlaytime;
		this.createDate = createDate;
	}

	@Override
	public MusicDto toDto() {
		ModelMapper modelMapper = new ModelMapper();
		MusicDto musicDto = modelMapper.map(this, MusicDto.class);
		musicDto.setMusicId(music.getMusicId());
		musicDto.setMusicArtistId(music.getMusicArtistId());
		musicDto.setMusicAlbumId(music.getMusicAlbumId());
		musicDto.setMusicCategoryId(music.getMusicCategoryId());
		return musicDto;
	}
}