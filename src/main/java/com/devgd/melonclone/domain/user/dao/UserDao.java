package com.devgd.melonclone.domain.user.dao;

import java.util.Optional;

import com.devgd.melonclone.domain.user.domain.UserEntity;
import com.devgd.melonclone.domain.user.dto.UserDto;
import com.devgd.melonclone.domain.user.exception.UserNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDao {
	
	private final UserRepository userRepository;

	public Integer save(UserDto userDto) {
		return userRepository.save(userDto.toEntity()).getUserId();
	}

	public UserEntity findByEmail(String userEmail) {
		final Optional<UserEntity> user = userRepository.findByEmail(userEmail);
		user.orElseThrow(() -> new UserNotFoundException(userEmail));

		return user.get();
	}

	public UserEntity findById(Integer userId) {
		final Optional<UserEntity> user = userRepository.findById(userId);
		user.orElseThrow(() -> new UserNotFoundException(userId+""));

		return user.get();
	}
}