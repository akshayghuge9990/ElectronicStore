package com.electronicstore.serviceI.Impl;

import com.electronicstore.Config.PageableResponse;
import com.electronicstore.dtos.CategoryDto;
import com.electronicstore.serviceI.CategoryServiceI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryServiceImpl implements CategoryServiceI {
    @Autowired
    private CategoryServiceI categoryServiceI;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {


        return null;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, String categoryId) {
        return null;
    }

    @Override
    public CategoryDto getSingleCategory(String categoryId) {
        return null;
    }

    @Override
    public PageableResponse<CategoryDto> getAllCatogory() {
        return null;
    }

    @Override
    public void deleteCategory(String categoryId) {

    }
}
