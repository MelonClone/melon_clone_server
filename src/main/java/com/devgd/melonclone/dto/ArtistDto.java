package com.devgd.melonclone.dto;

import java.time.LocalDateTime;

import com.devgd.melonclone.domain.entity.ArtistEntity;

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
	private Long id;
	private String name;
	private String profile;
	private String artistDesc;
	private LocalDateTime createDate;

	public ArtistEntity toEntity(){
		return ArtistEntity.builder()
				.artist_id(id)
				.artist_name(name)
				.artist_profile(profile)
				.artist_desc(artistDesc)
				.create_date(createDate)
				.build();
	}

	@Builder
	public ArtistDto(Long id, String name, String profile, String artistDesc, LocalDateTime creeateDate) {
		this.id = id;
		this.name = name;
		this.profile = profile;
		this.artistDesc = artistDesc;
		this.createDate = creeateDate;
	}
}