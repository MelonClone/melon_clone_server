package com.devgd.melonclone.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user_table")
public class UserEntity {
	@OneToMany
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long user_id;

	@Column(length = 45, nullable = false)
	private String email;

	@Column(length = 45, nullable = false)
	private String nickname;

	@Column(length = 255, nullable = false)
	private String password;

	@Column(length = 255, nullable = false)
	private String salt;

	@Column(nullable = false)
	private LocalDateTime create_date;

	@Column(nullable = true)
	private LocalDateTime last_login;

	@Builder
	public UserEntity(Long user_id, String email, String nickname,
			String password, String salt, LocalDateTime create_date, LocalDateTime last_login) {
		this.user_id = user_id;
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.salt = salt;
		this.create_date = create_date;
		this.last_login = last_login;
	}
}