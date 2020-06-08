	package com.devgd.melonclone.dto;

	import java.time.LocalDateTime;

	import com.devgd.melonclone.domain.entity.UserEntity;

	import lombok.Builder;
	import lombok.Getter;
	import lombok.NoArgsConstructor;
	import lombok.Setter;
	import lombok.ToString;

	@Getter
	@Setter
	@ToString
	@NoArgsConstructor
	public class UserDto {
		private Long id;
		private String email;
		private String password;
		private LocalDateTime createdDate;
		private LocalDateTime modifiedDate;

		public UserEntity toEntity(){
			return UserEntity.builder()
					.user_id(id)
					.email(email)
					.password(password)
					.build();
		}

		@Builder
		public UserDto(Long id, String email, String password) {
			this.id = id;
			this.email = email;
			this.password = password;
		}
	}