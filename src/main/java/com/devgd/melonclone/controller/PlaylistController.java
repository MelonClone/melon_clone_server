package com.devgd.melonclone.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/v1/playlist")
public class PlaylistController {
	
	@PostMapping(value = "/")
	public String addPlaylist() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}

	@GetMapping(value = "/")
	public String getPlaylists() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}

	@GetMapping(value = "/{list_id}")
	public String getPlaylistMusic() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}
	
	@PostMapping(value = "/{list_id}/item")
	public String addPlaylistItem() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}

	@DeleteMapping(value = "/{list_id}/item/{item_id}")
	public String removePlaylistItem() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}
}