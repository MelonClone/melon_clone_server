package com.devgd.melonclone.domain.playlist.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.devgd.melonclone.domain.model.BaseEntity;
import com.devgd.melonclone.domain.music.domain.MusicEntity;
import com.devgd.melonclone.domain.playlist.dto.PlaylistMusicDto;

import org.modelmapper.ModelMapper;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "playlist_music_table")
@NoArgsConstructor
public class PlaylistMusicEntity implements Serializable, BaseEntity<PlaylistMusicDto> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pm_id", nullable = false)
	private Integer pmId;

	@Column(name = "pm_order")
	private Integer pmOrder;

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
		ModelMapper modelMapper = new ModelMapper();
		PlaylistMusicDto playlistMusicDto = modelMapper.map(this, PlaylistMusicDto.class);
		playlistMusicDto.setPlaylistDto(pmPlaylist.toDto());
		playlistMusicDto.setMusicDto(pmMusic.toDto());
		return playlistMusicDto;
	}
}