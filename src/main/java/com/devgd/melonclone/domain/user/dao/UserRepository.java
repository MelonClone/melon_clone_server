package com.devgd.melonclone.domain.user.dao;

import java.util.Optional;

import com.devgd.melonclone.domain.user.domain.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	Optional<UserEntity> findByEmail(String userEmail);
	Optional<UserEntity> findByUserId(Integer userId);
}