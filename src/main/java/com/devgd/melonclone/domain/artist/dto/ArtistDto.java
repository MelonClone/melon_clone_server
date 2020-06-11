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
	private Long artistId;
	private String artistName;
	private String artistProfile;
	private String artistDesc;
	private LocalDateTime createDate;

	public ArtistEntity toEntity(){
		return ArtistEntity.builder()
				.artistId(artistId)
				.artistName(artistName)
				.artistProfile(artistProfile)
				.artistDesc(artistDesc)
				.createDate(createDate != null ? createDate : LocalDateTime.now())
				.build();
	}

	@Builder
	public ArtistDto(Long artistId, String artistName, String artistProfile, String artistDesc, LocalDateTime createDate) {
		this.artistId = artistId;
		this.artistName = artistName;
		this.artistProfile = artistProfile;
		this.artistDesc = artistDesc;
		this.createDate = createDate;
	}
}