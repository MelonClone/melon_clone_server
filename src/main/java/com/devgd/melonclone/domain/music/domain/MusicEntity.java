package com.devgd.melonclone.domain.music.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.devgd.melonclone.domain.album.domain.AlbumEntity;
import com.devgd.melonclone.domain.artist.domain.ArtistEntity;
import com.devgd.melonclone.domain.model.BaseEntity;
import com.devgd.melonclone.domain.music.dto.MusicDto;

import org.modelmapper.ModelMapper;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@Table(name = "music_table")
@NoArgsConstructor
@ToString
public class MusicEntity implements Serializable, BaseEntity {

	@Id
	@Column(name = "music_id", nullable = false)
	private String musicId;

	@OneToOne
	@JoinColumn(name ="music_artist_id", referencedColumnName = "artist_id", nullable = false)
	// @Column(name = "music_artist_id", nullable = false)
	private ArtistEntity musicArtist;

	@OneToOne
	@JoinColumn(name ="music_album_id", referencedColumnName = "album_id", nullable = false)
	// @Column(name = "music_album_id", nullable = false)
	private AlbumEntity musicAlbum;

	@ManyToOne
	@JoinColumn(name ="music_category_id", referencedColumnName = "category_id", nullable = false)
	private CategoryEntity musicCategory;

	@Column(name = "music_name", length = 45, nullable = false)
	private String musicName;

	@Column(name = "music_like", nullable = false)
	private Integer musicLike;

	@Column(name = "music_playtime", nullable = false)
	private Integer musicPlaytime;

	@Column(name = "create_date", nullable = false)
	private LocalDateTime createDate;

	@Builder
	public MusicEntity(String musicId, ArtistEntity musicArtist, AlbumEntity musicAlbum, CategoryEntity musicCategory,
			String musicName, Integer musicLike, Integer musicPlaytime, 
			LocalDateTime createDate) {
		this.musicId = musicId;
		this.musicName = musicName;
		this.musicLike = musicLike;
		this.musicCategory = musicCategory;
		this.musicPlaytime = musicPlaytime;
		this.musicArtist = musicArtist;
		this.musicAlbum = musicAlbum;
		this.createDate = createDate;
	}
}