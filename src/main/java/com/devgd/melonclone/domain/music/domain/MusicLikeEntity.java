package com.devgd.melonclone.domain.music.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.devgd.melonclone.domain.model.BaseEntity;
import com.devgd.melonclone.domain.music.dto.MusicLikeDto;

import org.modelmapper.ModelMapper;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "music_like_table")
@NoArgsConstructor
public class MusicLikeEntity implements Serializable, BaseEntity<MusicLikeDto> {

	@EmbeddedId
	private MusicLikeId mulId;

	@Column(name = "like_time", nullable = false)
	private LocalDateTime likeTime;

	public MusicLikeEntity(MusicLikeId mulId) {
		this.mulId = mulId;
	}

	@Builder
	public MusicLikeEntity(MusicLikeId mulId, LocalDateTime likeTime) {
		this.mulId = mulId;
		this.likeTime = likeTime;
	}

	@Override
	public MusicLikeDto toDto() {
		ModelMapper modelMapper = new ModelMapper();
		MusicLikeDto userDto = modelMapper.map(this, MusicLikeDto.class);
		userDto.setMusicId(mulId.getMulMusicId());
		userDto.setUserId(mulId.getMulUserId());
		userDto.setLikeTime(likeTime);

		return userDto;
	}
	
}