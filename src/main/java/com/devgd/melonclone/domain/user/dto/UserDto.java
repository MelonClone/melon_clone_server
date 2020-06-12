package com.devgd.melonclone.domain.user.dto;

import java.time.LocalDateTime;

import com.devgd.melonclone.domain.user.domain.AdminEntity;
import com.devgd.melonclone.domain.user.domain.UserEntity;
import com.devgd.melonclone.global.config.Role;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {
	private Integer userId;
	private String nickname;
	private String email;
	private String password;
	private LocalDateTime createDate = LocalDateTime.now();
	private LocalDateTime lastLogin;
	private Role role;

	public UserEntity toEntity(){
		return UserEntity.builder()
				.userId(userId)
				.nickname(nickname)
				.email(email)
				.password(password)
				.createDate(createDate)
				.lastLogin(lastLogin)
				.admin(new AdminEntity(userId, role))
				.build();
	}

	@Builder
	public UserDto(Integer userId, String email, String password) {
		this.userId = userId;
		this.email = email;
		this.password = password;
	}
}