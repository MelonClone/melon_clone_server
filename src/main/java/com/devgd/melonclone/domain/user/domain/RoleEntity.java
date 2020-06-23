package com.devgd.melonclone.domain.user.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role_table")
@NoArgsConstructor
public class RoleEntity implements Serializable {
	
	@Id
	@Column(name = "role_user_id", nullable = false)
	private Integer roleUserId;

	@Column(name = "role_name")
	private String roleName;

	@OneToOne
	@JoinColumn(name ="role_user_id", referencedColumnName = "user_id", nullable = false)
	private UserEntity roleUser;
	

	@Builder
	public RoleEntity(Integer roleUserId, String roleName, UserEntity roleUser) {
		this.roleUserId = roleUserId;
		this.roleName = roleName;
		this.roleUser = roleUser;
	}
}