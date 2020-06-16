package com.devgd.melonclone.domain.album.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.devgd.melonclone.domain.album.dto.AlbumDto;
import com.devgd.melonclone.domain.model.BaseEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "album_table")
public class AlbumEntity implements Serializable, BaseEntity<AlbumDto> {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer album_id;

	@Column(nullable = false)
	private Integer album_artist_id;

	@Column(length = 45, nullable = false)
	private String album_name;

	@Column(nullable = false)
	private Integer album_like;

	@Column(length = 255, nullable = false)
	private String album_jacket;

	@Column(nullable = false)
	private Integer album_category;

	@Column(nullable = false)
	private LocalDateTime create_date;

	@Builder
	public AlbumEntity(Integer album_id, Integer album_artist_id, 
			String album_name, Integer album_like,
			String album_jacket, Integer album_category, LocalDateTime create_date) {
		this.album_id = album_id;
		this.album_artist_id = album_artist_id;
		this.album_name = album_name;
		this.album_like = album_like;
		this.album_jacket = album_jacket;
		this.album_category = album_category;
		this.create_date = create_date;
	}

	@Override
	public AlbumDto toDto() {
		// TODO Auto-generated method stub
		return null;
	}
}