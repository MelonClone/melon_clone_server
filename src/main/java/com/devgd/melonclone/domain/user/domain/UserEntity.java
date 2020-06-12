package com.devgd.melonclone.domain.user.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.devgd.melonclone.domain.model.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user_table")
@NoArgsConstructor
public class UserEntity extends BaseEntity implements Serializable {
	
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer userId;

	@Column(name = "email", length = 45, nullable = false)
	private String email;

	@Column(name = "nickname", length = 45, nullable = false)
	private String nickname;

	@Column(name = "password", length = 255, nullable = false)
	private String password;

	@Column(name = "create_date", nullable = false)
	private LocalDateTime createDate;

	@Column(name = "last_login", nullable = true)
	private LocalDateTime lastLogin;

	@ManyToOne
	@JoinColumn(name ="admin_user_id", referencedColumnName = "admin_user_id")
	private AdminEntity admin;

	@Builder
	public UserEntity(Integer userId, String email, String nickname,
			String password, LocalDateTime createDate, LocalDateTime lastLogin, AdminEntity admin) {
		this.userId = userId;
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.createDate = createDate;
		this.lastLogin = lastLogin;
	}
}