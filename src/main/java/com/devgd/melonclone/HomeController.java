package com.devgd.melonclone;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("home")
@RequestMapping("/v1")
public class HomeController {
	
	@GetMapping(value = "/search/{search_type}")
	public String searchMusic() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}
	
}