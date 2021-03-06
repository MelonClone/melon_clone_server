package com.devgd.melonclone.domain.music.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LyricId implements Serializable {
	
	/**
	 *
	 */
	private static final long serialVersionUID = -1306848696971814629L;
	
	private Integer lyricId;
	private String lyricMusicId;

}