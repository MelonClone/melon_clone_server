package com.devgd.melonclone.domain.user.dto;

import java.time.LocalDateTime;

import com.devgd.melonclone.domain.user.domain.UserEntity;

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
	private Long id;
	private String nickname;
	private String email;
	private String password;
	private LocalDateTime createDate;
	private LocalDateTime lastLogin;

	public UserEntity toEntity(){
		return UserEntity.builder()
				.user_id(id)
				.nickname(nickname)
				.email(email)
				.password(password)
				.create_date(createDate)
				.last_login(lastLogin)
				.build();
	}

	@Builder
	public UserDto(Long id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}
}