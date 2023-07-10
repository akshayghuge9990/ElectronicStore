package com.electronicstore.controller;


import com.electronicstore.dtos.ProductDto;
import com.electronicstore.entity.Product;
import com.electronicstore.model.PageableResponse;
import com.electronicstore.serviceI.ProductServiceI;
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
import java.util.Date;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @MockBean
    private ProductServiceI productServiceI;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mockMvc;

    Product product;

    ProductDto productDto;

    ProductDto productDto1;

    ProductDto productDto2;

    ProductDto productDto3;

    List<ProductDto> productList;




    @BeforeEach
    void init(){

        product = Product.builder()
                .title("iphone Mobile")
                .description("This mobile provide for 10GB RAM,125GB internal storage,25000 battery ")
                .price(150000)
                .addedDate(new Date())
                .quantity(10)
                .live(true)
                .stock(false)
                .build();

        productDto = ProductDto.builder()
                .title("This is iphone Mobile")
                .description("This mobile is updated in product Service")
                .price(20000)
                .addedDate(new Date())
                .quantity(1)
                .live(true)
                .stock(false)
                .build();

        productDto1 = ProductDto.builder()
                .title("This is Samsang Mobile")
                .description("This mobile is updated in product Service")
                .price(10000)
                .addedDate(new Date())
                .quantity(10)
                .live(true)
                .stock(false)
                .build();

        productDto2 = ProductDto.builder()
                .title("This is POCO Mobile")
                .description("This mobile is updated in product Service")
                .price(30000)
                .addedDate(new Date())
                .quantity(4)
                .live(true)
                .stock(false)
                .build();

        productDto3 = ProductDto.builder()
                .title("This is Nokia Mobile")
                .description("This mobile is updated in product Service")
                .price(40000)
                .addedDate(new Date())
                .quantity(89)
                .live(true)
                .stock(false)
                .build();

        productList = Arrays.asList(productDto, productDto1, productDto2, productDto3);


    }

    // Create

    @Test
    void createProductTest() throws Exception {

        //  /products//product + POST request +json

        ProductDto dto = modelMapper.map(product, ProductDto.class);

        Mockito.when(productServiceI.create(Mockito.any())).thenReturn(dto);
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/products/product/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectTOJsonString(product))
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").exists());

            }

            // Convertting one Object to JSON Format

    private String convertObjectTOJsonString(Object product) {

        try {

            return new ObjectMapper().writeValueAsString(product);

        } catch (Exception ex) {

            ex.printStackTrace();
            return null;
        }
    }

    // update

    @Test
    void updateProductTest() throws Exception {

        // /products//product/{productId}+put+json

        String productId="234";

        ProductDto dto = modelMapper.map(product, ProductDto.class);

        Mockito.when(productServiceI.update(Mockito.any(),Mockito.anyString())).thenReturn(dto);

        this.mockMvc.perform(
                MockMvcRequestBuilders.put("/products/product/"+productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectTOJsonString(product))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").exists());

    }

    // Get All

    @Test
    void getAllProductTest() throws Exception {

        // /products/All+GET request+json

        PageableResponse<ProductDto> pagableResponse=new PageableResponse<>();

        pagableResponse.setContent(Arrays.asList(productDto,productDto1,productDto2,productDto3));
        pagableResponse.setLastPage(false);
        pagableResponse.setPageSize(10);
        pagableResponse.setPageNumber(100);
        pagableResponse.setTotalPages(1000);


        Mockito.when(productServiceI.getAllProduct(Mockito.anyInt(),Mockito.anyInt(), Mockito.anyString(),Mockito.anyString())).thenReturn(pagableResponse);

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/products/All")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                .andExpect(status().isOk());

    }

    //Get Single

    @Test
    void getSingleProduct() throws Exception {

        // /products/product/{productId}+GET Request+json

        String productId="2345";

        ProductDto dto = modelMapper.map(product, ProductDto.class);

        Mockito.when(productServiceI.getSingelProduct(Mockito.any())).thenReturn(dto);

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/products/product/"+productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                .andExpect(status().isOk());
    }

    // Delete

    @Test
    void deleteProductTest() throws Exception {

        String productId="1345";

       productServiceI.deleteProduct(productId);
       this.mockMvc.perform(
               MockMvcRequestBuilders.delete("/products/product/"+productId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .accept(MediaType.APPLICATION_JSON))
                       .andDo(print())
               .andExpect(status().isOk());




    }

    // Get All Live

    @Test
    void getAllLiveTest() throws Exception {

        // /products/search/{query}+Get request +json

        PageableResponse<ProductDto> pagableResponse=new PageableResponse<>();

        pagableResponse.setContent(Arrays.asList(productDto,productDto1,productDto2,productDto3));
        pagableResponse.setLastPage(false);
        pagableResponse.setPageSize(10);
        pagableResponse.setPageNumber(100);
        pagableResponse.setTotalPages(1000);


        Mockito.when(productServiceI.getAllLive(Mockito.anyInt(),Mockito.anyInt(), Mockito.anyString(),Mockito.anyString())).thenReturn(pagableResponse);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/products/product/live")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    // Get All Search

    @Test
    void getAllSerachTest() throws Exception {

        String query="AHSGD";

        PageableResponse<ProductDto> pagableResponse=new PageableResponse<>();

        pagableResponse.setContent(Arrays.asList(productDto,productDto1,productDto2,productDto3));
        pagableResponse.setLastPage(false);
        pagableResponse.setPageSize(10);
        pagableResponse.setPageNumber(100);
        pagableResponse.setTotalPages(1000);


        Mockito.when(productServiceI.searchByTitle(Mockito.any(), Mockito.anyInt(),Mockito.anyInt(), Mockito.anyString(),Mockito.anyString())).thenReturn(pagableResponse);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/products/search/"+query)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());





    }


}