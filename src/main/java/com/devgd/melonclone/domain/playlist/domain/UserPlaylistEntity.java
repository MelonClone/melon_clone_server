package com.devgd.melonclone.domain.playlist.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.devgd.melonclone.domain.model.BaseEntity;
import com.devgd.melonclone.domain.playlist.dto.UserPlaylistDto;
import com.devgd.melonclone.domain.user.domain.UserEntity;

import org.hibernate.annotations.OnDelete;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "up_id")
	private Integer upId;

	@ManyToOne
	@MapsId("up_user_id")
	@JoinColumn(name ="up_user_id", referencedColumnName = "user_id")
	private UserEntity upUser;

	@ManyToOne
	@MapsId("up_playlist_id")
	@JoinColumn(name ="up_playlist_id", referencedColumnName = "playlist_id")
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
		return modelMapper.map(this, UserPlaylistDto.class);
	}
}