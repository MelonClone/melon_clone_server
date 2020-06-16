package com.devgd.melonclone.domain.user.api;

import java.util.HashMap;
import java.util.Map;

import com.devgd.melonclone.domain.user.application.UserService;
import com.devgd.melonclone.domain.user.dto.UserDto;
import com.devgd.melonclone.global.common.response.SuccessResponse;
import com.devgd.melonclone.global.config.JwtTokenProvider;
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
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@PostMapping(value = "/regist")
	public SuccessResponse userRegist(@RequestBody UserDto userDto) {
		Integer userId = userService.joinUser(userDto);
		String resturnMsg = "Added user " + userDto.getEmail() + 
			" with ID " + userId;
		return new SuccessResponse(resturnMsg);
	}

	@PostMapping(value = "/login")
	public String userLogin(@RequestBody Map<String, Object> loginReq) {
		
		String email = (String) loginReq.get("email");
		String password = (String) loginReq.get("password");
		UserDto user = userService.authenticate(email, password);

		Map<String, String> publicUserInfo = new HashMap<String, String>();
		publicUserInfo.put("user_id", user.getUserId()+"");
		publicUserInfo.put("user_email", user.getEmail());
		publicUserInfo.put("user_name", user.getNickname());
		String accessToken = jwtTokenProvider.createToken(user.getUserId()+"", user.getRole(), publicUserInfo);
		return accessToken;
	}

	@DeleteMapping(value = "/logout")
	public String userLogout() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}
	
	
}