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

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "playlist_table")
@NoArgsConstructor
public class PlaylistEntity implements Serializable, BaseEntity {
	/**
	 *
	 */
	private static final long serialVersionUID = 3675191177373436211L;

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
}