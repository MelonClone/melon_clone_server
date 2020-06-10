package com.devgd.melonclone;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/v1")
public class HomeController {
	
	@GetMapping(value = "/search/{search_type}")
	public String searchMusic() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}
	
}