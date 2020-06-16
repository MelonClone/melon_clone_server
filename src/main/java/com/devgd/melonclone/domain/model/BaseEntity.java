package com.devgd.melonclone.domain.model;

public interface BaseEntity<T extends BaseDto> {
	T toDto();
}