package com.devgd.melonclone.domain.playlist.dto;

import com.devgd.melonclone.domain.model.BaseDto;
import com.devgd.melonclone.domain.playlist.domain.PlaylistEntity;
import com.devgd.melonclone.domain.playlist.domain.PlaylistMusicEntity;
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
public class PlaylistDto implements BaseDto<PlaylistEntity> {
	private Integer playlistId;
	private String playlistName;
	private Integer size;
	private Integer playtime;

	@Builder
	public PlaylistDto(Integer playlistId, String playlistName, Integer size, Integer playtime) {
		this.playlistId = playlistId;
		this.playlistName = playlistName;
		this.size = size;
		this.playtime = playtime;
	}
	
	@Override
	public PlaylistEntity toEntity(){
		return PlaylistEntity.builder()
			.playlistId(playlistId)
			.playlistName(playlistName)
			.build();
	}

	@Override
	public PlaylistDto parse(PlaylistEntity playlistEntity) {
		ModelMapperUtils.getModelMapper().map(playlistEntity, this);
		if (playlistEntity.getPlaylistMusics() != null) {
			this.setSize(playlistEntity.getPlaylistMusics().size());
			int playtime = 0;
			for (PlaylistMusicEntity playlistMusic : playlistEntity.getPlaylistMusics()) {
				if (playlistMusic.getPmMusic() != null && playlistMusic.getPmMusic().getMusicPlaytime() != null)
					playtime += playlistMusic.getPmMusic().getMusicPlaytime();
			}
			this.setPlaytime(playtime);
		} else {
			this.setSize(0);
			this.setPlaytime(0);
		}

		return this;
	}
}