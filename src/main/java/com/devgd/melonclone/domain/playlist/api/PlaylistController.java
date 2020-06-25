package com.devgd.melonclone.domain.playlist.api;

import java.util.List;

import com.devgd.melonclone.domain.music.dto.MusicDto;
import com.devgd.melonclone.domain.playlist.application.PlaylistService;
import com.devgd.melonclone.domain.playlist.dto.PlaylistDto;
import com.devgd.melonclone.domain.playlist.dto.PlaylistMusicDto;
import com.devgd.melonclone.domain.user.dto.UserDto;
import com.devgd.melonclone.global.common.response.SuccessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public List<PlaylistMusicDto> getPlaylistMusic(
		Authentication authentication,
		@PathVariable(name="list_id") final Integer playlistId) {
		UserDto userDto = (UserDto)authentication.getPrincipal();
		return playlistService.getPlaylistMusics(userDto, playlistId);
	}
	
	@PostMapping(value = "/{list_id}/item")
	public SuccessResponse addPlaylistItem(
		Authentication authentication,
		@PathVariable(name="list_id") final Integer playlistId,
		@RequestBody MusicDto musicDto) {
		UserDto userDto = (UserDto)authentication.getPrincipal();
		PlaylistMusicDto playlistMusicDto = playlistService.addPlaylistMusic(userDto, playlistId, musicDto.getMusicId());
		String resturnMsg = "Added playlist item into " + playlistMusicDto.getPlaylistName();
		return new SuccessResponse(resturnMsg);
	}

	@DeleteMapping(value = "/{list_id}/item/{item_id}")
	public SuccessResponse removePlaylistItem(
		Authentication authentication,
		@PathVariable(name="list_id") final Integer playlistId,
		@PathVariable(name="item_id") final Integer itemId) {
		UserDto userDto = (UserDto)authentication.getPrincipal();
		playlistService.deletePlaylistMusic(userDto, playlistId, itemId);
		String resturnMsg = "Remove playlist item " + itemId + " at playlist id " + playlistId;
		return new SuccessResponse(resturnMsg);
	}
}