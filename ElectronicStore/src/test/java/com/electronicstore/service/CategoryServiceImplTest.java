package com.electronicstore.service;

import com.electronicstore.dtos.CategoryDto;
import com.electronicstore.entity.Category;
import com.electronicstore.repository.CategoryRepo;
import com.electronicstore.serviceI.Impl.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CategoryServiceImplTest {

    @MockBean
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelmapper;

    @Autowired
    private CategoryServiceImpl categoryService;

    Category category;

    CategoryDto categoryDto;
    @BeforeEach
    void init(){
        category=Category.builder()
                .title("Title1")
                .Description("Descreption1")
                .coverImage("ABCD.jpg")
                .build();

    }

    //create
    @Test
    void createCategory() {

        //Arrange
        Mockito.when(categoryRepo.save(Mockito.any())).thenReturn(category);
        //Actual Call
        CategoryDto category1 = categoryService.createCategory(modelmapper.map(category, CategoryDto.class));

        System.out.println(category1.getTitle());
        //Assert
        Assertions.assertNotNull(category1);
        Assertions.assertEquals("Title1",category1.getTitle());


    }

    @Test
    void updateCategory() {

        String categoryId="categoryId";

        categoryDto=CategoryDto.builder()
                .title("Title1")
                .Description("Descreption1")
                .coverImage("ABCD.jpg")
                .build();

        //Arrange
        Mockito.when(categoryRepo.findById(Mockito.anyString())).thenReturn(Optional.of(category));
        Mockito.when(categoryRepo.save(Mockito.any())).thenReturn(category);
        //Actual Call
        CategoryDto updateCategory = categoryService.updateCategory(categoryDto, categoryId);

        System.out.println(updateCategory.getTitle());
        //Assert
        Assertions.assertNotNull(updateCategory);
        Assertions.assertEquals(categoryDto.getTitle(),updateCategory.getTitle());

    }

    @Test
    void getSingleCategory() {
    }

    @Test
    void getAllCatogory() {
    }

    @Test
    void deleteCategory() {
    }

    @Test
    void searchCategory() {
    }
}