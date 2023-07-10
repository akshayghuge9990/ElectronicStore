package com.electronicstore.controller;
import com.electronicstore.dtos.CategoryDto;
import com.electronicstore.entity.Category;
import com.electronicstore.model.ApiResponse;
import com.electronicstore.model.PageableResponse;
import com.electronicstore.serviceI.CategoryServiceI;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerTest {

    @MockBean
    private CategoryServiceI categoryServiceI;

    @Autowired
    private CategoryController categoryController;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mockMvc;

    Category category;

    CategoryDto categoryDto;

    CategoryDto categoryDto1;

    CategoryDto categoryDto2;

    List<CategoryDto> categoryDtoList;

    @BeforeEach
    void init(){

        category= Category.builder()
                .title("Title1")
                .Description("Descreption1")
                .coverImage("ICICI.jpg")
                .build();


        categoryDto= CategoryDto.builder()
                .title("Title1")
                .Description("Descreption1")
                .coverImage("AXIS.jpg")
                .build();

        categoryDto1= CategoryDto.builder()
                .title("Title3")
                .Description("Descreption2")
                .coverImage("IDBI.jpg")
                .build();

        categoryDto2= CategoryDto.builder()
                .title("Title3")
                .Description("Descreption3")
                .coverImage("HDFC.jpg")
                .build();

         categoryDtoList= Arrays.asList(categoryDto,categoryDto1,categoryDto2);

    }

    @Test
    void createCategoryTest() throws Exception {

        //  /categories/category + POST request + JSON
        // data as JSON + Status created

        CategoryDto dto = modelMapper.map(category, CategoryDto.class);

        Mockito.when(categoryServiceI.createCategory(Mockito.any())).thenReturn(dto);

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/categories/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectTOJsonString(category))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").exists());



    }

    private String convertObjectTOJsonString(Object category) {

        try {

            return new ObjectMapper().writeValueAsString(category);

        }catch (Exception ex){
            ex.printStackTrace();
            return null;

        }


    }

    @Test
    void updateCategoryTest() throws Exception {

        //   /categories/category/{categoryId}+PUT request+JSON

        String categoryId="AJDSHH";

        CategoryDto dto = modelMapper.map(category, CategoryDto.class);


        Mockito.when(categoryServiceI.updateCategory(Mockito.any(),Mockito.anyString())).thenReturn(dto);

        this.mockMvc.perform(
                MockMvcRequestBuilders.put("/categories//category/+category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectTOJsonString(category))
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").exists());


    }

    @Test
    void getSingleCategory() throws Exception {
        
        //    /categories/category/{catId} +Get request + JSON

        String categoryId="AJSGD";

        CategoryDto dto = modelMapper.map(category, CategoryDto.class);

        Mockito.when(categoryServiceI.getSingleCategory(Mockito.any())).thenReturn(dto);

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/categories/category/"+categoryId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());



    }

    @Test
    void getAllCategory() throws Exception {


        //   /categories/allCategory +GET request +JSON

        PageableResponse<CategoryDto> pagableResponse = new PageableResponse<>();

        pagableResponse.setContent(Arrays.asList(categoryDto, categoryDto1, categoryDto2));
        pagableResponse.setLastPage(false);
        pagableResponse.setPageSize(10);
        pagableResponse.setPageNumber(100);
        pagableResponse.setTotalPages(1000);
        
        Mockito.when(categoryServiceI.getAllCatogory(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyString(),Mockito.anyString())).thenReturn(pagableResponse);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/categories/allCategory/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void deleteCategory() throws Exception {

        //   /categories/category/{categoryId}

        String categoryId="ASDF";

        categoryServiceI.deleteCategory(categoryId);

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/categories/category/"+categoryId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void searchTitle() throws Exception {

        //  /categories/category/serach/{keyword}


        String keywords="Title1";

        List<CategoryDto> dto = categoryDtoList.stream().map((c) -> modelMapper.map(c, CategoryDto.class)).collect(Collectors.toList());
        Mockito.when(categoryServiceI.searchCategory(Mockito.any())).thenReturn(dto);

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/categories/category/serach/"+keywords)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());



    }
}