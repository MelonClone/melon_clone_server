package com.devgd.melonclone.domain.playlist.dto;

import com.devgd.melonclone.domain.model.BaseDto;
import com.devgd.melonclone.domain.music.domain.MusicEntity;
import com.devgd.melonclone.domain.playlist.domain.PlaylistEntity;
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
	private Integer itemId;
	private Integer order;
	private Integer playlistId;
	private String playlistName;
	private String musicId;
	private String musicName;
	private Integer albumId;
	private String albumName;
	private Integer artistId;
	private String artistName;
	private String jacket;

	// private PlaylistDto playlistDto;
	// private MusicDto musicDto;

	@Builder
	public PlaylistMusicDto(Integer pmId, Integer order, Integer playlistId, 
		String playlistName, String musicId, String musicName, Integer albumId, 
		String albumName, Integer artistId, String artistName, String jacket) {
		this.itemId = pmId;
		this.order = order;
		this.playlistId = playlistId;
		this.playlistName = playlistName;
		this.musicId = musicId;
		this.musicName = musicName;
		this.albumId = albumId;
		this.albumName = albumName;
		this.artistId = artistId;
		this.artistName = artistName;
		this.jacket = jacket;

	}
	
	@Override
	public PlaylistMusicEntity toEntity(){
		return PlaylistMusicEntity.builder()
			.pmId(itemId)
			.order(order)
			.pmPlaylist(PlaylistEntity.builder()
				.playlistId(playlistId)
				.build()
			)
			.pmMusic(MusicEntity.builder()
				.musicId(musicId)
				.build()
			)
			.build();
	}

	@Override
	public PlaylistMusicDto parse(PlaylistMusicEntity playlistMusicEntity) {
		PlaylistMusicDto playlistMusicDto = new PlaylistMusicDto(
			playlistMusicEntity.getPmId(),
			playlistMusicEntity.getPmOrder(),
			playlistMusicEntity.getPmPlaylist().getPlaylistId(),
			playlistMusicEntity.getPmPlaylist().getPlaylistName(),
			playlistMusicEntity.getPmMusic().getMusicId(),
			playlistMusicEntity.getPmMusic().getMusicName(),
			playlistMusicEntity.getPmMusic().getMusicAlbum().getAlbumId(),
			playlistMusicEntity.getPmMusic().getMusicAlbum().getAlbumName(),
			playlistMusicEntity.getPmMusic().getMusicArtist().getArtistId(),
			playlistMusicEntity.getPmMusic().getMusicArtist().getArtistName(),
			playlistMusicEntity.getPmMusic().getMusicAlbum().getAlbumJacket()
		);
		// playlistMusicDto.setItemId(pmId);
		// playlistMusicDto.setOrder(order);
		// playlistMusicDto.setPlaylistDto(pmPlaylist.toDto());
		// playlistMusicDto.setMusicDto(pmMusic.toDto());
		return playlistMusicDto;
	}

}