package com.electronicstore.service;

import com.electronicstore.dtos.CategoryDto;
import com.electronicstore.entity.Category;
import com.electronicstore.model.PageableResponse;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
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
    
    Category category1;
    
    Category category2;

    List<Category> categoryList;
    


    CategoryDto categoryDto;
    @BeforeEach
    void init(){
        category=Category.builder()
                .title("Title1")
                .Description("Descreption1")
                .coverImage("ABCD.jpg")
                .build();

        category1=Category.builder()
                .title("Title1")
                .Description("Descreption1")
                .coverImage("ABCD.jpg")
                .build();

        category2=Category.builder()
                .title("Title1")
                .Description("Descreption1")
                .coverImage("ABCD.jpg")
                .build();

         categoryList = Arrays.asList(category, category1, category2);

        categoryDto=CategoryDto.builder()
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
    void getSingleCategoryTest() {

        String categoryId="ASDF";
        //Arrange
        Mockito.when(categoryRepo.findById(Mockito.any())).thenReturn(Optional.of(category));
        //Actual call
        CategoryDto singleCategory = categoryService.getSingleCategory(categoryId);
        //Assert
        Assertions.assertNotNull(singleCategory);
        Assertions.assertEquals(categoryDto.getTitle(),singleCategory.getTitle(),"Title Not found");


    }

    @Test
    void getAllCatogory() {


        PageImpl<Category> page = new PageImpl<>(categoryList);
        //Arrange
        Mockito.when(categoryRepo.findAll((Pageable) Mockito.any())).thenReturn(page);
        //Actual call
        PageableResponse<CategoryDto> allCatogory = categoryService.getAllCatogory(1, 2, "Title", "Ascending");
        //Assert
        Assertions.assertEquals(3,allCatogory.getContent().size());



    }

    @Test
    void deleteCategoryTest() {

        String categoryId="ERYW";
        //Arrange
        Mockito.when(categoryRepo.findById("ERYW")).thenReturn(Optional.of(category));

        categoryService.deleteCategory(categoryId);

        Assertions.assertNotNull(categoryId);




    }

    @Test
    void searchCategory() {

        String keywords="Akshay";
        //Arrange
        Mockito.when(categoryRepo.findByTitleContaining(keywords)).thenReturn(Arrays.asList(category,category1,category2));
        //Actual call
        List<CategoryDto> categoryDtoList = categoryService.searchCategory(keywords);
        //Assert
        Assertions.assertEquals(3,categoryDtoList.size(),"search category not found");


    }
}