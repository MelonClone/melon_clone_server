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
import com.devgd.melonclone.domain.playlist.dto.UserPlaylistDto;
import com.devgd.melonclone.domain.user.domain.UserEntity;

import org.modelmapper.ModelMapper;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "user_playlist_table",
	uniqueConstraints={
		@UniqueConstraint(
			columnNames={"up_user_id","up_playlist_id"}
		)
	})
@NoArgsConstructor
public class UserPlaylistEntity implements Serializable, BaseEntity<UserPlaylistDto> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "up_id")
	private Integer upId;

	@ManyToOne
	@JoinColumn(name = "up_user_id", referencedColumnName = "user_id", nullable = false)
	private UserEntity upUser;

	@ManyToOne
	@JoinColumn(name ="up_playlist_id", referencedColumnName = "playlist_id", nullable = false)
	private PlaylistEntity upPlaylist;

	@Builder
	public UserPlaylistEntity(Integer upId, UserEntity upUser, PlaylistEntity upPlaylist) {
		this.upId = upId;
		this.upUser = upUser;
		this.upPlaylist = upPlaylist;
	}

	@Override
	public UserPlaylistDto toDto() {
		ModelMapper modelMapper = new ModelMapper();
		UserPlaylistDto userPlaylistDto = modelMapper.map(this, UserPlaylistDto.class);
		userPlaylistDto.setPlaylistDto(this.upPlaylist.toDto());
		userPlaylistDto.setUserDto(this.upUser.toDto());

		return userPlaylistDto;
	}
}