package com.devgd.melonclone.domain.music.api;

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

@RestController("music")
@RequestMapping("/v1/music")
public class MusicController {
	
	@Autowired
	MusicService musicService;

	@PostMapping()
	public SuccessResponse addMusic(
		Authentication authentication,
		@RequestBody MusicDto musicDto) {
		UserDto userDto = (UserDto)authentication.getPrincipal();
		Integer musicId = musicService.addMusic(musicDto, userDto);
		String resturnMsg = "Added music name " + musicDto.getMusicName() +
			" with ID " + musicId;
		return new SuccessResponse(resturnMsg);
	}

	@GetMapping(value = "/{music_id}")
	public MusicDto getMusic(
		@PathVariable(name = "music_id") Integer musicId) {
		return musicService.getMusic(musicId);
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