package com.devgd.melonclone.domain.album.api;

import java.util.List;

import com.devgd.melonclone.domain.album.application.AlbumService;
import com.devgd.melonclone.domain.album.dto.AlbumDto;
import com.devgd.melonclone.domain.music.application.MusicService;
import com.devgd.melonclone.domain.music.dto.MusicDto;
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

@RestController("album")
@RequestMapping("/v1/album")
public class AlbumController {

	@Autowired
	AlbumService albumService;
	
	@Autowired
	MusicService musicService;

	@PostMapping()
	public SuccessResponse addArtistAlbum(
		Authentication authentication,
		@RequestBody AlbumDto albumDto) {
		UserDto userDto = (UserDto)authentication.getPrincipal();
		Integer albumId = albumService.addAlbum(userDto, albumDto);
		String resturnMsg = "Added album name " + albumDto.getAlbumName() +
			" with ID " + albumId;
		return new SuccessResponse(resturnMsg);
	}

	@GetMapping(value = "/{album_id}")
	public AlbumDto getAlbums(
		@PathVariable(name="album_id") final Integer albumId) {
		return albumService.getAlbum(albumId);
	}
	
	
	@GetMapping(value = "/{album_id}/music_list")
	public List<MusicDto> getAlbumMusics(
		@PathVariable(name="album_id") final Integer albumId) {
		return musicService.getMusicsByAlbumId(albumId);
	}

	@PutMapping(value = "/{album_id}")
	public SuccessResponse changeArtistAlbum(
		Authentication authentication,
		@RequestBody AlbumDto albumDto,
		@PathVariable(name = "album_id") Integer albumId) {
		UserDto userDto = (UserDto)authentication.getPrincipal();
		albumDto.setAlbumId(albumId);
		albumService.updateAlbum(userDto, albumDto);
		String resturnMsg = "Updated album name " + albumDto.getAlbumName() +
			" with ID " + albumId;
		return new SuccessResponse(resturnMsg);
	}

	@DeleteMapping(value = "/{album_id}")
	public SuccessResponse deleteArtistAlbum(
		Authentication authentication,
		@PathVariable(name = "album_id") Integer albumId) {
		UserDto userDto = (UserDto)authentication.getPrincipal();
		albumService.deleteAlbum(userDto, albumId);
		String resturnMsg = "Delete album ID " + albumId;
		return new SuccessResponse(resturnMsg);
	}

	
}