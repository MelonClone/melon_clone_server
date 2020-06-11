package com.devgd.melonclone.domain.user.api;

import com.devgd.melonclone.domain.user.application.UserService;
import com.devgd.melonclone.domain.user.dto.UserDto;
import com.devgd.melonclone.global.common.response.SuccessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("user")
@RequestMapping("/v1/user")
public class MelonUserController {
	
	@Autowired
	UserService userService;

	@PostMapping(value = "/regist")
	public SuccessResponse userRegist(@RequestBody UserDto userDto) {
		System.out.println(userDto.toEntity().toString());
		Long userId = userService.joinUser(userDto);
		String resturnMsg = "Added user " + userDto.getEmail() + 
			" with ID " + userId;
		return new SuccessResponse(resturnMsg);
	}

	@PostMapping(value = "/login")
	public String userLogin() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}

	@DeleteMapping(value = "/login")
	public String userLogout() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}
	
	
}