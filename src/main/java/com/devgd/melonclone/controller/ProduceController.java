package com.devgd.melonclone.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/v1/produce")
public class ProduceController {
	
	@PostMapping(value = "/artist")
	public String addArtist() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}

	@DeleteMapping(value = "/artist/{artist_id}")
	public String removeArtist() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}
	
	
}