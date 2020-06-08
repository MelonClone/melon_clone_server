package com.devgd.melonclone.domain.entity;

import java.util.Date;

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
@Table(name = "artist_table")
public class ArtistEntity {
	@OneToMany
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long artist_id;

	@Column(length = 45, nullable = false)
	private String artist_name;

	@Column(length = 255, nullable = false)
	private String artist_profile;

	@Column(nullable = false)
	private String artist_desc;

	@Column(nullable = false)
	private Date create_date;


	@Builder
	public ArtistEntity(Long artist_id, String artist_name, 
			String artist_profile, String artist_desc, Date create_date) {
		this.artist_id = artist_id;
		this.artist_name = artist_name;
		this.artist_profile = artist_profile;
		this.artist_desc = artist_desc;
		this.create_date = create_date;
	}
}