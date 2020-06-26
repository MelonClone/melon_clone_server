package com.devgd.melonclone.domain.music.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.devgd.melonclone.domain.album.domain.AlbumEntity;
import com.devgd.melonclone.domain.artist.domain.ArtistEntity;
import com.devgd.melonclone.domain.model.BaseDto;
import com.devgd.melonclone.domain.music.domain.CategoryEntity;
import com.devgd.melonclone.domain.music.domain.MusicEntity;
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
public class MusicDto implements BaseDto<MusicEntity> {
	
	private String musicId;
	private String musicName;
	private Integer musicArtistId;
	private String musicArtistName;
	private Integer musicAlbumId;
	private String musicAlbumName;
	private Integer musicCategoryId;
	private String musicCategoryName;
	private Integer musicLike = 0;
	private Integer musicPlaytime = 0;
	private String jacket;
	private List<LyricDto> musicLyrics;
	private LocalDateTime createDate = LocalDateTime.now();
	
	@Builder
	public MusicDto(String musicId, String musicName, Integer musicArtistId, Integer musicAlbumId, 
		Integer musicCategoryId, String musicCategoryName, Integer musicLike, 
		Integer musicPlaytime, List<LyricDto> musicLyrics, LocalDateTime createDate) {
		this.musicId = musicId;
		this.musicName = musicName;
		this.musicArtistId = musicArtistId;
		this.musicAlbumId = musicAlbumId;
		this.musicCategoryId = musicCategoryId;
		this.musicCategoryName = musicCategoryName;
		this.musicLike = musicLike;
		this.musicPlaytime = musicPlaytime;
		this.musicLyrics = musicLyrics;
		this.createDate = createDate;
	}

	public String getMusicId() {
		return musicId != null ? musicId : ""+musicArtistId+"."+musicAlbumId+"."+new Date().getTime();
	}

	@Override
	public MusicEntity toEntity() {
		return MusicEntity.builder()
			.musicId(musicId != null ? musicId : ""+musicArtistId+"."+musicAlbumId+"."+new Date().getTime())
			.musicArtist(ArtistEntity.builder()
				.artistId(musicArtistId)
				.build()
			)
			.musicAlbum(AlbumEntity.builder()
				.albumId(musicAlbumId)
				.build()
			)
			.musicCategory(CategoryEntity.builder()
				.categoryId(musicCategoryId)
				.build()
			)
			.musicName(musicName)
			.musicLike(musicLike)
			.musicPlaytime(musicPlaytime)
			.createDate(createDate)
			.build();
	}

	@Override
	public MusicDto parse(MusicEntity musicEntity) {
		ModelMapperUtils.getModelMapper().map(musicEntity, this);
		this.setMusicArtistName(musicEntity.getMusicArtist().getArtistName());
		this.setMusicAlbumName(musicEntity.getMusicAlbum().getAlbumName());
		// musicDto.setMusicCategoryName(musicCategoryName);
		this.setJacket(musicEntity.getMusicAlbum().getAlbumJacket());
		return this;
	}
}