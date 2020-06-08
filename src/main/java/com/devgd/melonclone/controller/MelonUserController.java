package com.devgd.melonclone.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/v1/user")
public class MelonUserController {
	
	@PostMapping(value = "/regist")
	public String userRegist() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
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