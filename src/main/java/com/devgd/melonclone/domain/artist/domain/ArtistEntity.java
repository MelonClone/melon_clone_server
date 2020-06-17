package com.devgd.melonclone.domain.artist.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.devgd.melonclone.domain.album.domain.AlbumEntity;
import com.devgd.melonclone.domain.artist.dto.ArtistDto;
import com.devgd.melonclone.domain.model.BaseEntity;
import com.devgd.melonclone.domain.user.domain.UserEntity;

import org.modelmapper.ModelMapper;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@Table(name = "artist_table")
@ToString
@NoArgsConstructor
public class ArtistEntity implements Serializable, BaseEntity<ArtistDto> {
	@Id
	@Column(name = "artist_id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer artistId;
	
	@Column(name = "artist_name", length = 45, nullable = false)
	private String artistName;

	@Column(name = "artist_profile", length = 255, nullable = true)
	private String artistProfile;

	@Column(name = "artist_desc", nullable = true)
	private String artistDesc;

	@Column(name = "create_date", nullable = false)
	private LocalDateTime createDate;
	
	@OneToOne
	@JoinColumn(name ="artist_user_id", referencedColumnName = "user_id")
	private UserEntity artistUser;

	@OneToMany(mappedBy = "albumArtist", cascade = CascadeType.ALL)
	private List<AlbumEntity> artistAlbum;

	@Builder
	public ArtistEntity(Integer artistId, String artistName, 
			String artistProfile, String artistDesc, LocalDateTime createDate, UserEntity artistUser) {
		this.artistId = artistId;
		this.artistName = artistName;
		this.artistProfile = artistProfile;
		this.artistDesc = artistDesc;
		this.createDate = createDate;
		this.artistUser = artistUser;
	}

	@Override
	public ArtistDto toDto() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, ArtistDto.class);
	}
}