package com.devgd.melonclone.domain.playlist.dto;

import com.devgd.melonclone.domain.model.BaseDto;
import com.devgd.melonclone.domain.playlist.domain.UserPlaylistEntity;
import com.devgd.melonclone.domain.user.dto.UserDto;
import com.devgd.melonclone.global.common.util.ModelMapperUtils;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserPlaylistDto implements BaseDto<UserPlaylistEntity> {
	private Integer upId;
	private UserDto userDto;
	private PlaylistDto playlistDto;
	
	@Override
	public UserPlaylistEntity toEntity(){
		return UserPlaylistEntity.builder()
			.upId(upId)
			.upUser(userDto.toEntity())
			.upPlaylist(playlistDto.toEntity())
			.build();
	}

	@Builder
	public UserPlaylistDto(Integer upId, UserDto userDto, PlaylistDto playlistDto) {
		this.upId = upId;
		this.userDto = userDto;
		this.playlistDto = playlistDto;
	}
	
	@Override
	public UserPlaylistDto parse(UserPlaylistEntity userPlaylistEntity) {
		ModelMapperUtils.getModelMapper().map(userPlaylistEntity, this);
		this.setPlaylistDto(new PlaylistDto().parse(userPlaylistEntity.getUpPlaylist()));
		this.setUserDto(new UserDto().parse(userPlaylistEntity.getUpUser()));

		return this;
	}
}