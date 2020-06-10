package com.devgd.melonclone.domain.artist.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "artist_table")
public class ArtistEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long artist_id;

	@Column(length = 45, nullable = false)
	private String artist_name;

	@Column(length = 255, nullable = true)
	private String artist_profile;

	@Column(nullable = true)
	private String artist_desc;

	@Column(nullable = false)
	private LocalDateTime create_date;


	@Builder
	public ArtistEntity(Long artist_id, String artist_name, 
			String artist_profile, String artist_desc, LocalDateTime create_date) {
		this.artist_id = artist_id;
		this.artist_name = artist_name;
		this.artist_profile = artist_profile;
		this.artist_desc = artist_desc;
		this.create_date = create_date;
	}
}