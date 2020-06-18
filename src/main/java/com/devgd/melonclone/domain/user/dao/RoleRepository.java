package com.devgd.melonclone.domain.user.dao;

import com.devgd.melonclone.domain.user.domain.RoleEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
}