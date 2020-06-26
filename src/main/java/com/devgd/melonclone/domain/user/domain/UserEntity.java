package com.devgd.melonclone.domain.user.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.devgd.melonclone.domain.artist.domain.ArtistEntity;
import com.devgd.melonclone.domain.model.BaseEntity;
import com.devgd.melonclone.domain.playlist.domain.UserPlaylistEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user_table")
@NoArgsConstructor
public class UserEntity implements Serializable, BaseEntity {
	
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer userId;

	@Column(name = "email", length = 45, nullable = false)
	private String email;

	@Column(name = "nickname", length = 45, nullable = false)
	private String nickname;

	@Column(name = "password", length = 255, nullable = false)
	private String password;

	@Column(name = "create_date", nullable = false)
	private LocalDateTime createDate;

	@Column(name = "last_login", nullable = true)
	private LocalDateTime lastLogin;

	@Column(name = "activate", nullable = false)
	private Boolean activate = true;

	@Column(name = "disable_date", nullable = true)
	private LocalDateTime disableDate;

	@OneToOne(mappedBy = "roleUser", cascade = CascadeType.ALL)
	private RoleEntity role;

	@OneToOne(mappedBy = "artistUser", cascade = CascadeType.ALL)
	private ArtistEntity artist;

	@OneToMany(mappedBy = "upUser", cascade = CascadeType.ALL)
	private Set<UserPlaylistEntity> userPlaylist;

	@Builder
	public UserEntity(Integer userId, String email, String nickname,
			String password, LocalDateTime createDate, LocalDateTime lastLogin, 
			Boolean activate, LocalDateTime disableDate, 
			RoleEntity role, ArtistEntity artist, Set<UserPlaylistEntity> userPlaylist) {
		this.userId = userId;
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.createDate = createDate;
		this.lastLogin = lastLogin;
		this.activate = activate;
		this.disableDate = disableDate;
		this.role = role;
		this.artist = artist;
		this.userPlaylist = userPlaylist;
	}
}