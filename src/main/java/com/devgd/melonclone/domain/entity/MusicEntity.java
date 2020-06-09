package com.devgd.melonclone.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "music_table",
	uniqueConstraints={
		@UniqueConstraint(
			columnNames={"music_id","music_artist_id",
				"music_album_id", "music_category_id"}
		)
	}
)
public class MusicEntity {

	@ManyToMany
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long music_id;

	@Column(nullable = false)
	private Long music_artist_id;

	@Column(nullable = false)
	private Long music_album_id;

	@Column(nullable = false)
	private Long music_category_id;

	@Column(length = 45, nullable = false)
	private String music_name;

	@Column(nullable = false)
	private Long music_like;

	@Column(nullable = false)
	private Long music_playtime;

	@Column(nullable = false)
	private LocalDateTime create_date;

	@Builder
	public MusicEntity(Long music_id, Long music_artist_id, 
			Long music_album_id, Long music_category_id, 
			String music_name, Long music_like, Long music_playtime, 
			LocalDateTime create_date) {
		this.music_id = music_id;
		this.music_artist_id = music_artist_id;
		this.music_album_id = music_album_id;
		this.music_category_id = music_category_id;
		this.music_name = music_name;
		this.music_like = music_like;
		this.music_playtime = music_playtime;
		this.create_date = create_date;
	}
}