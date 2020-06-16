package com.devgd.melonclone.domain.model;

public interface BaseDto<T extends BaseEntity> {
	T toEntity();
}