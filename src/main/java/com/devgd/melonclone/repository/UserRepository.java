package com.devgd.melonclone.repository;

import java.util.Optional;

import com.devgd.melonclone.domain.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByEmail(String userEmail);
}