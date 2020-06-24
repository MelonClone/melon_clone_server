package com.devgd.melonclone.domain.playlist.dto;

import com.devgd.melonclone.domain.model.BaseDto;
import com.devgd.melonclone.domain.music.dto.MusicDto;
import com.devgd.melonclone.domain.playlist.domain.PlaylistMusicEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PlaylistMusicDto implements BaseDto<PlaylistMusicEntity> {
	private Integer pmId;
	private Integer order;
	private PlaylistDto playlistDto;
	private MusicDto musicDto;
	
	@Override
	public PlaylistMusicEntity toEntity(){
		return PlaylistMusicEntity.builder()
			.pmId(pmId)
			.order(order)
			.pmPlaylist(playlistDto.toEntity())
			.pmMusic(musicDto.toEntity())
			.build();
	}

	@Builder
	public PlaylistMusicDto(Integer pmId, Integer order, PlaylistDto playlistDto, MusicDto musicDto) {
		this.pmId = pmId;
		this.order = order;
		this.musicDto = musicDto;
		this.playlistDto = playlistDto;
	}
	
}