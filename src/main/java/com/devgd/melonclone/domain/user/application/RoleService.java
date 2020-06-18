package com.devgd.melonclone.domain.user.application;

import com.devgd.melonclone.domain.model.Role;
import com.devgd.melonclone.domain.user.dao.UserDao;
import com.devgd.melonclone.domain.user.domain.UserEntity;
import com.devgd.melonclone.domain.user.dto.UserDto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleService {
	private UserDao userDao;

	@Transactional
	public boolean changeRole(Integer userId, Role role) {
		UserEntity userEntity = userDao.findById(userId);
		UserDto userDto = userEntity.toDto();
		userDto.setRole(role);
		userDao.saveRole(userDto);
		return true;
	}
}