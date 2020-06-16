package com.devgd.melonclone.domain.artist.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.devgd.melonclone.domain.artist.dto.ArtistDto;
import com.devgd.melonclone.domain.model.BaseEntity;

import org.modelmapper.ModelMapper;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@Table(name = "artist_table")
@ToString
@NoArgsConstructor
public class ArtistEntity implements Serializable, BaseEntity<ArtistDto> {
	@Id
	@Column(name = "artist_id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer artistId;

	@Column(name = "artist_name", length = 45, nullable = false)
	private String artistName;

	@Column(name = "artist_profile", length = 255, nullable = true)
	private String artistProfile;

	@Column(name = "artist_desc", nullable = true)
	private String artistDesc;

	@Column(name = "create_date", nullable = false)
	private LocalDateTime createDate;


	@Builder
	public ArtistEntity(Integer artistId, String artistName, 
			String artistProfile, String artistDesc, LocalDateTime createDate) {
		this.artistId = artistId;
		this.artistName = artistName;
		this.artistProfile = artistProfile;
		this.artistDesc = artistDesc;
		this.createDate = createDate;
	}

	@Override
	public ArtistDto toDto() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, ArtistDto.class);
	}
}