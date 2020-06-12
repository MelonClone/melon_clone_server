package com.devgd.melonclone.domain.user.application;

import java.util.ArrayList;
import java.util.List;

import com.devgd.melonclone.domain.user.dao.UserDao;
import com.devgd.melonclone.domain.user.domain.UserEntity;
import com.devgd.melonclone.domain.user.dto.UserDto;
import com.devgd.melonclone.domain.user.exception.UserNotFoundException;
import com.devgd.melonclone.domain.user.exception.UserVerifyException;
import com.devgd.melonclone.global.config.Role;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(user, UserDto.class);
		Role userRole = user.getAdmin() != null ? user.getAdmin().getAdminRole() : Role.MEMBER;
		userDto.setRole(userRole);
		return userDto;
	}

	public UserDetails getUserByUsername(String userEmail) {
		UserEntity user = userDao.findByEmail(userEmail);

		List<GrantedAuthority> authorities = new ArrayList<>();

		if (("admin@example.com").equals(userEmail)) {
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
		} else {
			authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
		}

		return new User(user.getEmail(), user.getPassword(), authorities);
	}

	public UserDetails getUserById(Integer userId) {
		UserEntity user = userDao.findById(userId);

		List<GrantedAuthority> authorities = new ArrayList<>();

		if (user.getAdmin().getAdminRole() == Role.ADMIN) {
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
		} else {
			authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
		}

		return new User(user.getEmail(), user.getPassword(), authorities);
	}
}