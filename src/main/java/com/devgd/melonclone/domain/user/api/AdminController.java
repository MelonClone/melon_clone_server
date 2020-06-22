package com.devgd.melonclone.domain.user.api;

import com.devgd.melonclone.domain.user.application.UserService;
import com.devgd.melonclone.domain.user.dto.UserDto;
import com.devgd.melonclone.global.common.response.SuccessResponse;
import com.devgd.melonclone.global.config.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("admin")
@RequestMapping("/v1/admin")
public class AdminController {
	
	@Autowired
	UserService userService;

	@PostMapping(value = "/user")
	public SuccessResponse registUser(@RequestBody UserDto userDto) {
		Integer userId = userService.joinUser(userDto);
		String resturnMsg = "Added user " + userDto.getEmail() + 
			" with ID " + userId;
		return new SuccessResponse(resturnMsg);
	}

	@DeleteMapping(value = "/user/{user_id}")
	public void diableUser(
		@PathVariable(name = "user_id") Integer userId) {
		userService.disableUser(userId);
	}
	
	
}