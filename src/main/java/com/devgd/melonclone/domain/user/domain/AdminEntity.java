package com.devgd.melonclone.domain.user.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.devgd.melonclone.global.config.Role;

import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "admin_table")
public class AdminEntity implements Serializable {
	
	@Id
	@Column(name = "admin_user_id")
	private Integer adminUserId;

	@Column(name = "admin_role")
	private Role adminRole;

	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
	private List<UserEntity> admin_users = new ArrayList<>();
	

	@Builder
	public AdminEntity(Integer adminUserId, Role adminRole) {
		this.adminUserId = adminUserId;
		this.adminRole = adminRole;
	}
}