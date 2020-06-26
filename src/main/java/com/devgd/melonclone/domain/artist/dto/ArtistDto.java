package com.devgd.melonclone.domain.artist.dto;

import java.time.LocalDateTime;

import com.devgd.melonclone.domain.artist.domain.ArtistEntity;
import com.devgd.melonclone.domain.model.BaseDto;
import com.devgd.melonclone.global.common.util.ModelMapperUtils;

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
	private Integer artistUserId;
	private String artistName;
	private String artistProfile;
	private String artistDesc;
	private LocalDateTime createDate = LocalDateTime.now();

	@Builder
	public ArtistDto(Integer artistId, Integer artistUserId, String artistName, String artistProfile, String artistDesc, LocalDateTime createDate) {
		this.artistId = artistId;
		this.artistUserId = artistUserId;
		this.artistName = artistName;
		this.artistProfile = artistProfile;
		this.artistDesc = artistDesc;
		this.createDate = createDate;
	}

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

	@Override
	public ArtistDto parse(ArtistEntity artistEntity) {
		ModelMapperUtils.getModelMapper().map(artistEntity, this);
		return this;
	}

}