package com.devgd.melonclone.domain.album.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "album_table")
public class AlbumEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long album_id;

	@Column(nullable = false)
	private Long album_artist_id;

	@Column(length = 45, nullable = false)
	private String album_name;

	@Column(nullable = false)
	private Long album_like;

	@Column(length = 255, nullable = false)
	private String album_jacket;

	@Column(nullable = false)
	private Long album_category;

	@Column(nullable = false)
	private LocalDateTime create_date;

	@Builder
	public AlbumEntity(Long album_id, Long album_artist_id, 
			String album_name, Long album_like,
			String album_jacket, Long album_category, LocalDateTime create_date) {
		this.album_id = album_id;
		this.album_artist_id = album_artist_id;
		this.album_name = album_name;
		this.album_like = album_like;
		this.album_jacket = album_jacket;
		this.album_category = album_category;
		this.create_date = create_date;
	}
}