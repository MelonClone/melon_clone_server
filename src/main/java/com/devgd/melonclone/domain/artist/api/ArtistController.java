package com.devgd.melonclone.domain.artist.api;

import com.devgd.melonclone.domain.artist.application.ArtistService;
import com.devgd.melonclone.domain.artist.dao.ArtistRepository;
import com.devgd.melonclone.domain.artist.dto.ArtistDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;

@RestController("artist")
@RequestMapping("/v1/artist")
public class ArtistController {
	
	@Autowired
	ArtistService artistService;

	@GetMapping(value = "/{artist_id}")
	public ArtistDto getArtist(
		@PathVariable(name="artist_id") final Integer artist_id) {
		return artistService.getArtist(artist_id);
	}

	@PostMapping(value = "/{artist_id}/like")
	public void like(
		Authentication authentication,
		@PathVariable(name="artist_id") final Integer artist_id) {
		Claims claims = (Claims)authentication.getPrincipal();
		Integer user_id = claims.get("user_id", Integer.class);
		artistService.addLike(artist_id, user_id);
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
	
}