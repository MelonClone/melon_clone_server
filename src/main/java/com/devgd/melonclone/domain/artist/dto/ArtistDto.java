package com.devgd.melonclone.domain.artist.dto;

import java.time.LocalDateTime;

import com.devgd.melonclone.domain.artist.domain.ArtistEntity;
import com.devgd.melonclone.domain.model.BaseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ArtistDto implements BaseDto<ArtistEntity> {
	private Integer artistId;
	private String artistName;
	private String artistProfile;
	private String artistDesc;
	private LocalDateTime createDate = LocalDateTime.now();

	@Override
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