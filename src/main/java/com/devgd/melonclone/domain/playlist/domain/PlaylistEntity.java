package com.devgd.melonclone.domain.playlist.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.devgd.melonclone.domain.model.BaseEntity;
import com.devgd.melonclone.domain.playlist.dto.PlaylistDto;

import org.modelmapper.ModelMapper;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "playlist_table")
@NoArgsConstructor
public class PlaylistEntity implements Serializable, BaseEntity<PlaylistDto> {
	@Id
	@Column(name = "playlist_id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer playlistId;

	@Column(name = "playlist_name", length = 45, nullable = false)
	private String playlistName;
	
	@OneToMany(mappedBy = "upPlaylist", cascade = CascadeType.ALL)
	private Set<UserPlaylistEntity> userPlaylists;

	@OneToMany(mappedBy = "pmPlaylist", cascade = CascadeType.ALL)
	private Set<PlaylistMusicEntity> playlistMusics;

	@Builder
	public PlaylistEntity(Integer playlistId, String playlistName, Set<UserPlaylistEntity> userPlaylists, Set<PlaylistMusicEntity> playlistMusics) {
		this.playlistId = playlistId;
		this.playlistName = playlistName;
		this.userPlaylists = userPlaylists;
		this.playlistMusics = playlistMusics;
	}

	@Override
	public PlaylistDto toDto() {
		ModelMapper modelMapper = new ModelMapper();
		PlaylistDto playtimeDto = modelMapper.map(this, PlaylistDto.class);
		if (playlistMusics != null) {
			playtimeDto.setSize(playlistMusics.size());
			int playtime = 0;
			for (PlaylistMusicEntity playlistMusic : playlistMusics) {
				if (playlistMusic.getPmMusic() != null && playlistMusic.getPmMusic().getMusicPlaytime() != null)
					playtime += playlistMusic.getPmMusic().getMusicPlaytime();
			}
			playtimeDto.setPlaytime(playtime);
		} else {
			playtimeDto.setSize(0);
			playtimeDto.setPlaytime(0);
		}

		return playtimeDto;
	}
}