package com.devgd.melonclone.domain.album.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.devgd.melonclone.domain.album.dto.AlbumDto;
import com.devgd.melonclone.domain.artist.domain.ArtistEntity;
import com.devgd.melonclone.domain.model.BaseEntity;

import org.modelmapper.ModelMapper;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "album_table")
@NoArgsConstructor
public class AlbumEntity implements Serializable, BaseEntity<AlbumDto> {
	@Id
	@Column(name = "album_id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer albumId;

	@Column(name = "album_name", length = 45, nullable = false)
	private String albumName;

	@Column(name = "album_like", nullable = false)
	private Integer albumLike = 0;

	@Column(name = "album_jacket", length = 255, nullable = false)
	private String albumJacket = "http://defualt.img";

	@Column(name = "album_category_id", nullable = false)
	private Integer albumCategoryId;

	@Column(name = "create_date", nullable = false)
	private LocalDateTime createDate = LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name ="album_artist_id", referencedColumnName = "artist_id", nullable = false)
	private ArtistEntity albumArtist;

	@Builder
	public AlbumEntity(Integer albumId, String albumName, Integer albumLike,
			String albumJacket, Integer albumCategoryId, LocalDateTime createDate, 
			ArtistEntity albumArtist) {
		this.albumId = albumId;
		this.albumName = albumName;
		this.albumLike = albumLike;
		this.albumJacket = albumJacket;
		this.albumCategoryId = albumCategoryId;
		this.createDate = createDate;
		this.albumArtist = albumArtist;
	}

	@Override
	public AlbumDto toDto() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, AlbumDto.class);
	}
}