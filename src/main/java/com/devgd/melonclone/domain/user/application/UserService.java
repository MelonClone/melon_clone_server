package com.devgd.melonclone.domain.user.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.devgd.melonclone.domain.user.dao.UserRepository;
import com.devgd.melonclone.domain.user.domain.UserEntity;
import com.devgd.melonclone.domain.user.dto.UserDto;
import com.devgd.melonclone.global.config.Role;

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
public class UserService implements UserDetailsService {
	private UserRepository userRepository;

	@Transactional
	public Long joinUser(UserDto userDto) {
		// 비밀번호 암호화
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

		return userRepository.save(userDto.toEntity()).getUser_id();
	}

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		Optional<UserEntity> userEntityWrapper = userRepository.findByEmail(userEmail);
		UserEntity userEntity = userEntityWrapper.get();

		List<GrantedAuthority> authorities = new ArrayList<>();

		if (("admin@example.com").equals(userEmail)) {
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
		} else {
			authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
		}

		return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
	}
}