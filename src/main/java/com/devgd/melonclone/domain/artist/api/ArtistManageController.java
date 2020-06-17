package com.devgd.melonclone.domain.artist.api;

import java.util.Map;

import com.devgd.melonclone.domain.album.application.AlbumService;
import com.devgd.melonclone.domain.artist.application.ArtistService;
import com.devgd.melonclone.domain.artist.dto.ArtistDto;
import com.devgd.melonclone.domain.user.dto.UserDto;
import com.devgd.melonclone.global.common.response.SuccessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;

@RestController("artist_manage")
@RequestMapping("/v1/artist_manage")
public class ArtistManageController {
	
	@Autowired
	ArtistService artistService;

	@Autowired
	AlbumService albumService;

	@PutMapping(value = "/{artist_id}")
	public SuccessResponse changeArtist(
		Authentication authentication,
		@RequestBody ArtistDto artistDto,
		@PathVariable(name="artist_id") final Integer artistId) {
		UserDto userDto = (UserDto)authentication.getPrincipal();
		artistDto.setArtistId(artistId);
		
		artistService.updateArtist(userDto.getUserId(), artistDto);

		return new SuccessResponse("success");
	}

	@DeleteMapping(value = "/{artist_id}/album/{album_id}")
	public String remoteArtistAlbum() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}

	@PutMapping(value = "/{artist_id}/album/{album_id}")
	public String updateArtistAlbum() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}
	
}