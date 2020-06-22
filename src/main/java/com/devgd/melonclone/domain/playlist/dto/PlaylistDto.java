package com.devgd.melonclone.domain.playlist.dto;

import com.devgd.melonclone.domain.model.BaseDto;
import com.devgd.melonclone.domain.playlist.domain.PlaylistEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PlaylistDto implements BaseDto<PlaylistEntity> {
	private Integer playlistId;
	private String playlistName;
	
	@Override
	public PlaylistEntity toEntity(){
		return PlaylistEntity.builder()
			.playlistId(playlistId)
			.playlistName(playlistName)
			.build();
	}

	@Builder
	public PlaylistDto(Integer playlistId, String playlistName) {
		this.playlistId = playlistId;
		this.playlistName = playlistName;
	}
	
}