package com.hriday.blogapis.services;

import com.hriday.blogapis.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

     CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
    void deleteteCategory(Integer categoryId);
    CategoryDto getCategory(Integer categoryId);
    List<CategoryDto> getAllCategory();

}
