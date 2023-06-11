package com.electronicstore.serviceI;

import com.electronicstore.Config.PageableResponse;
import com.electronicstore.dtos.CategoryDto;

public interface CategoryServiceI {


    //create

    CategoryDto createCategory(CategoryDto categoryDto);


    //update

   CategoryDto updateCategory(CategoryDto categoryDto,String categoryId);


    //getSingleCategory

    CategoryDto getSingleCategory(String categoryId);



    //getAllCategory

    PageableResponse<CategoryDto> getAllCatogory();


    //Delete

    void deleteCategory(String categoryId);


    //Search







}
