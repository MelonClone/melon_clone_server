package com.devgd.melonclone.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/v1/artist")
public class ArtistController {
	
	
	@PostMapping(value = "/{artist_id}/like")
	public String like() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}

	@GetMapping(value = "/{artist_id}/music_list")
	public String getArtistMusics() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}

	@GetMapping(value = "/{artist_id}/album_list")
	public String getArtistAlbums() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}

	@PostMapping(value = "/{artist_id}/album")
	public String addArtistAlbum() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}

	@DeleteMapping(value = "/{artist_id}/album/{album_id}")
	public String remoteArtistAlbum() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}

	@PutMapping(value = "/{artist_id}/album/{album_id}")
	public String updateArtistAlbum() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}

	@GetMapping(value = "/{artist_id}/album/{album_id}/music_list")
	public String getArtistAlbumMusics() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}
	
	@DeleteMapping(value = "/{artist_id}/album/{album_id}")
	public String removeArtistAlbum() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}
}