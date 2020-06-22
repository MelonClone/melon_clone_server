package com.devgd.melonclone.domain.artist.application;

import com.devgd.melonclone.domain.artist.dao.ArtistDao;
import com.devgd.melonclone.domain.artist.domain.ArtistEntity;
import com.devgd.melonclone.domain.artist.dto.ArtistDto;
import com.devgd.melonclone.domain.artist.exception.ArtistPermissionException;
import com.devgd.melonclone.domain.user.dao.UserDao;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArtistService {

	private final ArtistDao artistDao;
	private final UserDao userDao;

	public boolean checkArtistAuth(Integer artistId, Integer userId) {
		if (artistDao.getArtistWithUserId(artistId, userId) == null) return false;
		return true;
	}

	public Integer addArtist(ArtistDto artistDto) {
		ArtistEntity artistEntity = ArtistEntity.builder()
				.artistId(artistDto.getArtistId())
				.artistName(artistDto.getArtistName())
				.artistProfile(artistDto.getArtistProfile())
				.artistDesc(artistDto.getArtistProfile())
				.createDate(artistDto.getCreateDate())
				.artistUser(userDao.findById(artistDto.getArtistUserId()))
				.build();
		return artistDao.save(artistEntity);
	}

	public ArtistDto getArtist(Integer artistId) {
		return artistDao.getArtist(artistId).toDto();
	}

	public boolean updateArtist(Integer userId, ArtistDto artistDto) {
		ArtistEntity originArtist = artistDao.getArtist(artistDto.getArtistId());
		ArtistDto originArtistDto = originArtist.toDto();
		if (userId != originArtistDto.getArtistUserId()) throw new ArtistPermissionException();
		
		if (artistDto.getArtistName() != null) originArtistDto.setArtistName(artistDto.getArtistName());
		if (artistDto.getArtistDesc() != null) originArtistDto.setArtistDesc(artistDto.getArtistDesc());
		if (artistDto.getArtistProfile() != null) originArtistDto.setArtistProfile(artistDto.getArtistProfile());
		ArtistEntity artistEntity = ArtistEntity.builder()
				.artistId(originArtistDto.getArtistId())
				.artistName(originArtistDto.getArtistName())
				.artistProfile(originArtistDto.getArtistProfile())
				.artistDesc(originArtistDto.getArtistDesc())
				.createDate(originArtistDto.getCreateDate())
				.artistUser(originArtist.getArtistUser())
				.build();

		return artistDao.updateArtist(artistEntity);
	}

	public boolean removeArtist(ArtistDto artistDto) {
		return artistDao.removeArtist(artistDto.toEntity());
	}
	
	public boolean removeArtistById(Integer artistId) {
		return artistDao.removeArtistById(artistId);
	}

	public void changeLike(Integer artistId, Integer userId) {
		if (artistDao.isLike(artistId, userId)) artistDao.removeLike(artistId, userId);
		else artistDao.addLike(artistId, userId);
	}
}