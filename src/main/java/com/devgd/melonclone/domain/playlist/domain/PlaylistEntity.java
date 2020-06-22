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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
	
	@OneToMany(mappedBy = "upUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<UserPlaylistEntity> userPlaylist;

	@Builder
	public PlaylistEntity(Integer playlistId, String playlistName) {
		this.playlistId = playlistId;
		this.playlistName = playlistName;
	}

	@Override
	public PlaylistDto toDto() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, PlaylistDto.class);
	}
}