package com.devgd.melonclone.domain.user.dto;

import java.time.LocalDateTime;

import com.devgd.melonclone.domain.model.BaseDto;
import com.devgd.melonclone.domain.model.Role;
import com.devgd.melonclone.domain.user.domain.RoleEntity;
import com.devgd.melonclone.domain.user.domain.UserEntity;
import com.devgd.melonclone.domain.user.domain.UserEntity.UserEntityBuilder;
import com.devgd.melonclone.global.common.util.ModelMapperUtils;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto implements BaseDto<UserEntity> {
	private Integer userId;
	private String nickname;
	private String email;
	private String password;
	private LocalDateTime createDate = LocalDateTime.now();
	private LocalDateTime lastLogin;
	private Role role;
	private Boolean activate = true;
	private LocalDateTime disableDate;

	@Builder
	public UserDto(Integer userId, String email, String password) {
		this.userId = userId;
		this.email = email;
		this.password = password;
	}

	@Override
	public UserEntity toEntity(){
		UserEntityBuilder userEntityBuilder = UserEntity.builder()
				.userId(userId)
				.nickname(nickname)
				.email(email)
				.password(password)
				.createDate(createDate)
				.lastLogin(lastLogin)
				.activate(activate)
				.disableDate(disableDate);

		if (role != null) {
			RoleEntity roleEntity = new RoleEntity();
			roleEntity.setRoleUserId(userId);
			roleEntity.setRoleName(role.name());
			userEntityBuilder.role(roleEntity);
		}
		return userEntityBuilder.build();
	}

	@Override
	public UserDto parse(UserEntity userEntity) {
		ModelMapperUtils.getModelMapper().map(userEntity, this);
		Role userRole = userEntity.getRole() != null ? Role.valueOf(userEntity.getRole().getRoleName()) : Role.MEMBER;
		this.setRole(userRole);

		return this;
	}
}