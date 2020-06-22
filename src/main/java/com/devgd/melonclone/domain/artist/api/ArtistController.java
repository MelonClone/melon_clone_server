package com.devgd.melonclone.domain.artist.api;

import java.util.List;

import com.devgd.melonclone.domain.album.application.AlbumService;
import com.devgd.melonclone.domain.album.dto.AlbumDto;
import com.devgd.melonclone.domain.artist.application.ArtistService;
import com.devgd.melonclone.domain.artist.dto.ArtistDto;
import com.devgd.melonclone.domain.music.application.MusicService;
import com.devgd.melonclone.domain.music.dto.MusicDto;
import com.devgd.melonclone.domain.user.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("artist")
@RequestMapping("/v1/artist")
public class ArtistController {
	
	@Autowired
	ArtistService artistService;
	
	@Autowired
	MusicService musicService;

	@Autowired
	AlbumService albumService;

	@GetMapping(value = "/{artist_id}")
	public ArtistDto getArtist(
		@PathVariable(name="artist_id") final Integer artistId) {
		return artistService.getArtist(artistId);
	}

	@PostMapping(value = "/{artist_id}/like")
	public void like(
		Authentication authentication,
		@PathVariable(name="artist_id") final Integer artistId) {
		UserDto userDto = (UserDto)authentication.getPrincipal();
		artistService.changeLike(artistId, userDto.getUserId());
	}

	@GetMapping(value = "/{artist_id}/music_list")
	public List<MusicDto> getArtistMusics(
		@PathVariable(name="artist_id") final Integer artistId) {
		return musicService.getMusicsByArtistId(artistId);
	}

	@GetMapping(value = "/{artist_id}/album_list")
	public List<AlbumDto> getArtistAlbums(
		@PathVariable(name="artist_id") final Integer artistId) {
		return albumService.getAlbumsByArtistId(artistId);
	}
	
	@GetMapping(value = "/{artist_id}/album/{album_id}/music_list")
	public String getArtistAlbumMusics() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}
	
}