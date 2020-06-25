package com.devgd.melonclone.domain.playlist.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.devgd.melonclone.domain.model.BaseEntity;
import com.devgd.melonclone.domain.music.domain.MusicEntity;
import com.devgd.melonclone.domain.playlist.dto.PlaylistMusicDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "playlist_music_table",
	uniqueConstraints={
		@UniqueConstraint(
			columnNames={"pm_order","pm_playlist_id"}
		)
	})
@NoArgsConstructor
public class PlaylistMusicEntity implements Serializable, BaseEntity<PlaylistMusicDto>, Comparable<PlaylistMusicEntity> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pm_id", nullable = false)
	private Integer pmId;

	@Column(name = "pm_order")
	private Integer pmOrder;
	// WARN pmOrder 가 pmPlaylist 안에서 찾을 수 없는 경우 해당 값들 삭제
	
	@ManyToOne
	@JoinColumn(name = "pm_playlist_id", referencedColumnName = "playlist_id", nullable = false)
	private PlaylistEntity pmPlaylist;

	@ManyToOne
	@JoinColumn(name ="pm_music_id", referencedColumnName = "music_id", nullable = false)
	private MusicEntity pmMusic;

	@Builder
	public PlaylistMusicEntity(Integer pmId, Integer order, PlaylistEntity pmPlaylist, MusicEntity pmMusic) {
		this.pmId = pmId;
		this.pmOrder = order;
		this.pmPlaylist = pmPlaylist;
		this.pmMusic = pmMusic;
	}

	@Override
	public PlaylistMusicDto toDto() {
		PlaylistMusicDto playlistMusicDto = new PlaylistMusicDto(
			pmId,
			pmOrder,
			pmPlaylist.getPlaylistId(),
			pmPlaylist.getPlaylistName(),
			pmMusic.getMusicId(),
			pmMusic.getMusicName(),
			pmMusic.getMusicAlbum().getAlbumId(),
			pmMusic.getMusicAlbum().getAlbumName(),
			pmMusic.getMusicArtist().getArtistId(),
			pmMusic.getMusicArtist().getArtistName(),
			pmMusic.getMusicAlbum().getAlbumJacket()
		);
		// playlistMusicDto.setItemId(pmId);
		// playlistMusicDto.setOrder(order);
		// playlistMusicDto.setPlaylistDto(pmPlaylist.toDto());
		// playlistMusicDto.setMusicDto(pmMusic.toDto());
		return playlistMusicDto;
	}

	@Override
	public int compareTo(PlaylistMusicEntity target) {
		if (this.getPmOrder() == null) return 1;
		else if (this.getPmId() == target.getPmOrder()) return -1;
		else return 0;
	}
}