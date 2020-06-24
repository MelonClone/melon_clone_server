package com.devgd.melonclone.domain.playlist.api;

import java.util.List;

import com.devgd.melonclone.domain.playlist.application.PlaylistService;
import com.devgd.melonclone.domain.playlist.dto.PlaylistDto;
import com.devgd.melonclone.domain.user.dto.UserDto;
import com.devgd.melonclone.global.common.response.SuccessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("playlist")
@RequestMapping("/v1/playlist")
public class PlaylistController {
	
	@Autowired
	PlaylistService playlistService;

	@PostMapping()
	public SuccessResponse addPlaylist(
		Authentication authentication,
		@RequestBody PlaylistDto playlistDto) {
		UserDto userDto = (UserDto)authentication.getPrincipal();
		Integer playlistId = playlistService.addPlaylist(userDto, playlistDto);
		String resturnMsg = "Added playlist name " + playlistDto.getPlaylistName() +
			" with ID " + playlistId;
		return new SuccessResponse(resturnMsg);
	}

	@GetMapping()
	public List<PlaylistDto> getPlaylists(
		Authentication authentication) {
		UserDto userDto = (UserDto)authentication.getPrincipal();
		List<PlaylistDto> playlists = playlistService.getUserPlaylists(userDto);
		return playlists;
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