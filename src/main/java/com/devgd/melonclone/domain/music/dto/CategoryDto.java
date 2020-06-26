package com.devgd.melonclone.domain.music.dto;

import com.devgd.melonclone.domain.model.BaseDto;
import com.devgd.melonclone.domain.music.domain.CategoryEntity;
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
public class CategoryDto implements BaseDto<CategoryEntity> {
	
	private Integer categoryId;
	private String categoryName;
	
	@Builder
	public CategoryDto(Integer categoryId, String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	@Override
	public CategoryEntity toEntity() {
		return CategoryEntity.builder()
			.categoryId(categoryId)
			.categoryName(categoryName)
			.build();
	}

	@Override
	public CategoryDto parse(CategoryEntity categoryEntity) {
		ModelMapperUtils.getModelMapper().map(categoryEntity, this);
		return this;
	}
}