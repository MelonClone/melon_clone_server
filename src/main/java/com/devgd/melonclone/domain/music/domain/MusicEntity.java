package com.devgd.melonclone.domain.music.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.devgd.melonclone.domain.model.BaseEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Embeddable
@Table(name = "music_table")
public class MusicEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -390950220271593086L;

	@EmbeddedId
	private MusicId id;

	@Column(length = 45, nullable = false)
	private String music_name;

	@Column(nullable = false)
	private Integer music_like;

	@Column(nullable = false)
	private Integer music_playtime;

	@Column(nullable = false)
	private LocalDateTime create_date;

	@Builder
	public MusicEntity(MusicId id, 
			String music_name, Integer music_like, Integer music_playtime, 
			LocalDateTime create_date) {
		this.id = id;
		this.music_name = music_name;
		this.music_like = music_like;
		this.music_playtime = music_playtime;
		this.create_date = create_date;
	}
}