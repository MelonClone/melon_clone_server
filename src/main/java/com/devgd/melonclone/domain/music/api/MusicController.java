package com.devgd.melonclone.domain.music.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/v1/music")
public class MusicController {
	
	@PostMapping(value = "/")
	public String addMusic() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}

	@GetMapping(value = "/{music_id}")
	public String getMusic() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}

	@DeleteMapping(value = "/{music_id}")
	public String removeMusic() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}

	@PutMapping(value = "/{music_id")
	public String updateMusic() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}

	@PostMapping(value = "/{music_id}/like")
	public String like() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}

	@GetMapping(value = "/{music_id}/lyrics")
	public String getLyrics() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}
	
	@GetMapping(value = "/{music_id}/similer")
	public String getMusicSimiler() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}
}