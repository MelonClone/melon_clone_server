package com.devgd.melonclone.domain.album.application;

import java.util.ArrayList;
import java.util.List;

import com.devgd.melonclone.domain.album.dao.AlbumDao;
import com.devgd.melonclone.domain.album.domain.AlbumEntity;
import com.devgd.melonclone.domain.album.dto.AlbumDto;
import com.devgd.melonclone.domain.album.exception.AlbumNotFoundException;
import com.devgd.melonclone.domain.artist.dao.ArtistDao;
import com.devgd.melonclone.domain.artist.domain.ArtistEntity;
import com.devgd.melonclone.domain.artist.exception.ArtistNotFoundException;
import com.devgd.melonclone.domain.user.dto.UserDto;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AlbumService {

	private final AlbumDao albumDao;
	private final ArtistDao artistDao;

	public boolean checkAlbumAuth(Integer albumId, Integer artistId, Integer userId) {
		ArtistEntity artistEntity = artistDao.getArtistWithUserId(artistId, userId);
		if (artistEntity == null) return false;
		if (albumDao.getAlbumWithArtist(albumId, artistEntity) == null) return false;
		return true;
	}

	public AlbumEntity checkAlbumAuth(Integer albumId, Integer userId) {
		ArtistEntity artistEntity = artistDao.getArtistByUserId(userId);
		if (artistEntity == null) throw new ArtistNotFoundException("user id "+userId);

		AlbumEntity albumEntity = albumDao.getAlbumWithArtist(albumId, artistEntity);
		if (albumEntity == null) throw new AlbumNotFoundException(""+albumId);
		return albumEntity;
	}

	public ArtistEntity checkAlbumCreateAuth(Integer userId) {
		ArtistEntity artistEntity = artistDao.getArtistByUserId(userId);
		if (artistEntity == null) throw new ArtistNotFoundException("user id "+userId);
		else return artistEntity;
	}

	public Integer addAlbum(UserDto userDto, AlbumDto albumDto) {
		AlbumEntity albumEntity = albumDto.toEntity();
		albumEntity.setAlbumArtist(checkAlbumCreateAuth(userDto.getUserId()));
		return albumDao.save(albumEntity);
	}

	public AlbumDto getAlbum(Integer albumId) {
		return new AlbumDto().parse(albumDao.getAlbum(albumId));
	}

	public List<AlbumDto> getAlbumsByArtistId(Integer artistId) {
		List<AlbumEntity> albumEntityList = albumDao.getAlbumByArtistId(artistId);
		List<AlbumDto> albumDtoList = new ArrayList<>();
		for (int i=0; i<albumEntityList.size(); i++) {
			AlbumEntity albumEntity = albumEntityList.get(i);
			albumDtoList.add(new AlbumDto().parse(albumEntity));
		}

		return albumDtoList;
	}

	public boolean updateAlbum(UserDto userDto, AlbumDto albumDto) {
		AlbumEntity albumEntity = albumDto.toEntity();
		albumEntity.setAlbumArtist(checkAlbumCreateAuth(userDto.getUserId()));
		
		AlbumEntity originalEntity = albumDao.getAlbum(albumDto.getAlbumId());
		if (albumEntity.getAlbumName() != null) originalEntity.setAlbumName(albumEntity.getAlbumName());
		if (albumEntity.getAlbumJacket() != null) originalEntity.setAlbumJacket(albumEntity.getAlbumJacket());
		if (albumEntity.getAlbumCategoryId() != null) originalEntity.setAlbumCategoryId(albumEntity.getAlbumCategoryId());
		
		albumDao.save(originalEntity);
		return true;
	}

	public boolean deleteAlbum(UserDto userDto, AlbumDto albumDto) {
		return this.deleteAlbum(userDto, albumDto.getAlbumId());
	}
	
	public boolean deleteAlbum(UserDto userDto, Integer albumId) {
		checkAlbumAuth(albumId, userDto.getUserId());
		albumDao.removeAlbum(albumId);

		return true;
	}
}