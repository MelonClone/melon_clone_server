package com.devgd.melonclone.domain.user.application;

import java.time.LocalDateTime;

import com.devgd.melonclone.domain.user.dao.UserDao;
import com.devgd.melonclone.domain.user.domain.UserEntity;
import com.devgd.melonclone.domain.user.dto.UserDto;
import com.devgd.melonclone.domain.user.exception.UserVerifyException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	private UserDao userDao;

	@Transactional
	public Integer joinUser(UserDto userDto) {
		// 비밀번호 암호화
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

		return userDao.save(userDto);
	}

	public UserDto authenticate(String userEmail, String password) {
		UserEntity user = userDao.findByEmail(userEmail);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new UserVerifyException();
		}
		return new UserDto().parse(user);
	}

	public void updateLastLogin(UserDto userDto) {
		userDto.setLastLogin(LocalDateTime.now());
		userDao.save(userDto);
	}

	public UserDto getUserByUsername(String userEmail) {
		return new UserDto().parse(userDao.findByEmail(userEmail));
	}

	public UserDto getUserById(Integer userId) {
		return new UserDto().parse(userDao.findById(userId));
	}

	public void disableUser(Integer userId) {
		UserEntity userEntity = userDao.findById(userId);
		UserDto userDto = new UserDto().parse(userEntity);
		userDto.setActivate(false);
		userDto.setDisableDate(LocalDateTime.now());
		userDao.save(userDto);
	}
}