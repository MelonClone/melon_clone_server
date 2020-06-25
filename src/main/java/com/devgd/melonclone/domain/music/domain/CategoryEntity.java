package com.devgd.melonclone.domain.music.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.devgd.melonclone.domain.model.BaseEntity;
import com.devgd.melonclone.domain.music.dto.CategoryDto;

import org.modelmapper.ModelMapper;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "category_table")
@NoArgsConstructor
public class CategoryEntity implements Serializable, BaseEntity<CategoryDto> {
	
	@Id
	@Column(name = "category_id", nullable = false)
	private Integer categoryId;
	
	@Column(name = "category_name", nullable = false)
	private String categoryName;

	@OneToMany(mappedBy = "musicCategory", cascade = CascadeType.ALL)
	private Set<MusicEntity> musicList;
	
	@Builder
	public CategoryEntity(Integer categoryId, String categoryName, Set<MusicEntity> musicList) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.musicList = musicList;
	}

	@Override
	public CategoryDto toDto() {
		ModelMapper modelMapper = new ModelMapper();
		CategoryDto categoryDto = modelMapper.map(this, CategoryDto.class);
		return categoryDto;
	}
}