package com.devgd.melonclone.domain.user.api;

import java.util.Map;

import com.devgd.melonclone.domain.artist.application.ArtistService;
import com.devgd.melonclone.domain.artist.dto.ArtistDto;
import com.devgd.melonclone.global.common.response.SuccessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("produce")
@RequestMapping("/v1/produce")
public class ProduceController {
	
	@Autowired
	ArtistService artistService;

	@PostMapping(value = "/artist")
	// public String addArtist(@RequestBody Map<String, Object> params) {
	// 	String artistName = (String) params.get("artist_name");
	public SuccessResponse addArtist(@RequestBody ArtistDto artistDto) {
		System.out.println(artistDto.toEntity().toString());
		Long artistId = artistService.addArtist(artistDto);
		String resturnMsg = "Added artist " + artistDto.getArtistName() + 
			" with ID " + artistId;
		return new SuccessResponse(resturnMsg);
	}

	@DeleteMapping(value = "/artist/{artist_id}")
	public String removeArtist() {
		return "{\"coffee\":{\"name\":\"americano\"}}";
	}
	
	
}