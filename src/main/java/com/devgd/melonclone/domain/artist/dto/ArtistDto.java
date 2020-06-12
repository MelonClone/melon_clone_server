package com.devgd.melonclone.domain.artist.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import com.devgd.melonclone.domain.artist.domain.ArtistEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ArtistDto {
	private Integer artistId;
	private String artistName;
	private String artistProfile;
	private String artistDesc;
	private LocalDateTime createDate = LocalDateTime.now();

	public ArtistEntity toEntity(){
		return ArtistEntity.builder()
				.artistId(artistId)
				.artistName(artistName)
				.artistProfile(artistProfile)
				.artistDesc(artistDesc)
				.createDate(createDate)
				.build();
	}

	@Builder
	public ArtistDto(Integer artistId, String artistName, String artistProfile, String artistDesc, LocalDateTime createDate) {
		this.artistId = artistId;
		this.artistName = artistName;
		this.artistProfile = artistProfile;
		this.artistDesc = artistDesc;
		this.createDate = createDate;
	}
}