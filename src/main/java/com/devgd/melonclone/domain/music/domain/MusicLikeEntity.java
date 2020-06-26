package com.devgd.melonclone.domain.music.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.devgd.melonclone.domain.model.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "music_like_table")
@NoArgsConstructor
public class MusicLikeEntity implements Serializable, BaseEntity {

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
	
}