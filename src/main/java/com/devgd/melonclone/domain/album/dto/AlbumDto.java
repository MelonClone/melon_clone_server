package com.devgd.melonclone.domain.album.dto;

import java.time.LocalDateTime;

import com.devgd.melonclone.domain.album.domain.AlbumEntity;
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
public class AlbumDto implements BaseDto<AlbumEntity> {
	private Integer albumId;
	private Integer albumArtistId;
	private String albumName;
	private Integer albumLike = 0;
	private String albumJacket = "http://default.img";
	private Integer albumCategoryId;
	private LocalDateTime createDate = LocalDateTime.now();
	
	@Override
	public AlbumEntity toEntity(){
		return AlbumEntity.builder()
			.albumId(albumId)
			.albumName(albumName)
			.albumLike(albumLike)
			.albumJacket(albumJacket)
			.albumCategoryId(albumCategoryId)
			.createDate(createDate)
			.build();
	}

	@Builder
	public AlbumDto(Integer albumId, Integer albumArtistId, String albumName, 
		Integer albumLike, String albumJacket, Integer albumCategoryId, LocalDateTime createDate) {
		this.albumId = albumId;
		this.albumArtistId = albumArtistId;
		this.albumName = albumName;
		this.albumLike = albumLike;
		this.albumJacket = albumJacket;
		this.albumCategoryId = albumCategoryId;
		this.createDate = createDate;
	}
	
}