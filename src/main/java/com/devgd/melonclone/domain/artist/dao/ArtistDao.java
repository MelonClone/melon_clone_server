package com.devgd.melonclone.domain.artist.dao;

import java.time.LocalDateTime;
import java.util.Optional;

import com.devgd.melonclone.domain.artist.domain.ArtistEntity;
import com.devgd.melonclone.domain.artist.domain.ArtistLikeEntity;
import com.devgd.melonclone.domain.artist.domain.ArtistLikeId;
import com.devgd.melonclone.domain.artist.exception.ArtistNotFoundException;
import com.devgd.melonclone.domain.user.dao.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ArtistDao {
	private final ArtistRepository artistRepository;
	private final ArtistLikeRepository artistLikeRepository;
	private final UserRepository userRepository;

	public Integer save(ArtistEntity artistEntity) {
		return artistRepository.save(artistEntity).getArtistId();
	}

	public ArtistEntity getArtist(Integer artistId) {
		final Optional<ArtistEntity> artist = artistRepository.findById(artistId);
		artist.orElseThrow(() -> new ArtistNotFoundException(artistId+""));
		return artist.get();
	}

	public ArtistEntity getArtistByUserId(Integer userId) {
		final Optional<ArtistEntity> artist = artistRepository.findByArtistUser(userRepository.findByUserId(userId).get());
		artist.orElseThrow(() -> new ArtistNotFoundException("User Id "+ userId));
		return artist.get();
	}

	public ArtistEntity getArtistWithUserId(Integer artistId, Integer userId) {
		final Optional<ArtistEntity> artist = artistRepository.findByArtistIdAndArtistUser(artistId, userRepository.findByUserId(userId).get());
		artist.orElseThrow(() -> new ArtistNotFoundException(artistId+""));
		return artist.get();
	}

	public boolean updateArtist(ArtistEntity artistEntity) {
		artistRepository.save(artistEntity);
		return true;
	}

	public boolean removeArtist(ArtistEntity artistEntity) {
		artistRepository.delete(artistEntity);
		return true;
	}

	public boolean removeArtistById(Integer artistId) {
		artistRepository.deleteById(artistId);
		return true;
	}

	public boolean isLike(Integer artistId, Integer userId) {
		return !artistLikeRepository.findByArlIdArlArtistIdAndArlIdArlUserId(artistId, userId).isEmpty();
	}

	public void addLike(Integer artistId, Integer userId) {
		artistLikeRepository.save(new ArtistLikeEntity(new ArtistLikeId(artistId, userId), LocalDateTime.now()));
	}

	public void removeLike(Integer artistId, Integer userId) {
		artistLikeRepository.delete(new ArtistLikeEntity(new ArtistLikeId(artistId, userId)));
	}
}