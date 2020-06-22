package com.devgd.melonclone.domain.user.api;

import com.devgd.melonclone.domain.artist.application.ArtistService;
import com.devgd.melonclone.domain.artist.dto.ArtistDto;
import com.devgd.melonclone.domain.model.Role;
import com.devgd.melonclone.domain.user.application.RoleService;
import com.devgd.melonclone.domain.user.application.UserService;
import com.devgd.melonclone.global.common.response.SuccessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("produce")
@RequestMapping("/v1/produce")
public class ProduceController {
	
	@Autowired
	ArtistService artistService;

	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;

	@PostMapping(value = "/artist")
	public SuccessResponse addArtist(
		@RequestBody ArtistDto artistDto) {
		Integer artistId = artistService.addArtist(artistDto);
		roleService.changeRole(artistDto.getArtistUserId(), Role.ARTIST);
		String resturnMsg = "Added artist " + artistDto.getArtistName() + 
			" with ID " + artistId;
		return new SuccessResponse(resturnMsg);
	}

	@DeleteMapping(value = "/artist/{artist_id}")
	public SuccessResponse removeArtist(
		@PathVariable(name="artist_id") final Integer artistId) {
		artistService.removeArtistById(artistId);
		String resturnMsg = "Deleted artist ID " + artistId;
		return new SuccessResponse(resturnMsg);
	}
	
	
}