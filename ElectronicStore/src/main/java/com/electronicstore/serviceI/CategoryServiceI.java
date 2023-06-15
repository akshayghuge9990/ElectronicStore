package com.electronicstore.serviceI;

import com.electronicstore.model.PageableResponse;
import com.electronicstore.dtos.CategoryDto;

import java.util.List;

public interface CategoryServiceI {


    //create

    CategoryDto createCategory(CategoryDto categoryDto);


    //update

    CategoryDto updateCategory(CategoryDto categoryDto, String categoryId);


    //getSingleCategory

    CategoryDto getSingleCategory(String categoryId);


    //getAllCategory

    PageableResponse<CategoryDto> getAllCatogory(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);


    //Delete

    void deleteCategory(String categoryId);


    //Search

    List<CategoryDto> searchCategory(String keyword);


}
