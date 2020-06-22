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
	private final RoleRepository roleRepository;

	public Integer save(UserDto userDto) {
		return userRepository.save(userDto.toEntity()).getUserId();
	}

	public void saveRole(UserDto userDto) {
		roleRepository.save(userDto.toEntity().getRole());
	}

	public UserEntity findByEmail(String userEmail) {
		return userRepository.findByEmail(userEmail)
			.orElseThrow(() -> new UserNotFoundException(userEmail));
	}

	public UserEntity findById(Integer userId) {
		return userRepository.findByUserId(userId)
			.orElseThrow(() -> new UserNotFoundException(userId+""));
	}
}