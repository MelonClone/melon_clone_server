package com.devgd.melonclone.repository;

import java.util.Optional;

import com.devgd.melonclone.domain.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByEmail(String userEmail);
}