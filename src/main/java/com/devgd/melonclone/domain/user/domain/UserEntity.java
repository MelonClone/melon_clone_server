package com.devgd.melonclone.domain.user.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.devgd.melonclone.domain.artist.domain.ArtistEntity;
import com.devgd.melonclone.domain.model.BaseEntity;
import com.devgd.melonclone.domain.model.Role;
import com.devgd.melonclone.domain.user.dto.UserDto;

import org.modelmapper.ModelMapper;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user_table")
@NoArgsConstructor
public class UserEntity implements Serializable, BaseEntity<UserDto> {
	
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

	@OneToOne(mappedBy = "roleUser", cascade = CascadeType.ALL)
	private RoleEntity role;

	@OneToOne(mappedBy = "artistUser", cascade = CascadeType.ALL)
	private ArtistEntity artist;

	@Builder
	public UserEntity(Integer userId, String email, String nickname,
			String password, LocalDateTime createDate, LocalDateTime lastLogin, RoleEntity role, ArtistEntity artist) {
		this.userId = userId;
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.createDate = createDate;
		this.lastLogin = lastLogin;
		this.role = role;
		this.artist = artist;
	}

	@Override
	public UserDto toDto() {
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(this, UserDto.class);
		Role userRole = this.role != null ? Role.valueOf(this.role.getRoleName()) : Role.MEMBER;
		userDto.setRole(userRole);

		return userDto;
	}
}