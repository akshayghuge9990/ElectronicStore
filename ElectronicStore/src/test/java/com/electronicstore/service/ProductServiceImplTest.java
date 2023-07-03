package com.electronicstore.service;

import com.electronicstore.dtos.ProductDto;
import com.electronicstore.entity.Product;
import com.electronicstore.repository.ProductRepo;
import com.electronicstore.serviceI.Impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceImplTest {

    @MockBean
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductServiceImpl productService;

    Product product;

    ProductDto productDto;

@BeforeEach
    public void init(){

        product=Product.builder()
                .title("This is iphone Mobile")
                .description("This mobile provide for 10GB RAM,125GB internal storage,25000 battery ")
                .price(150000)
                .addedDate(new Date())
                .quantity(10)
                .live(true)
                .stock(false)
                .build();

    }

    @Test
    void createTest() {

    //Arrange
        Mockito.when(productRepo.save(Mockito.any())).thenReturn(product);

        //Actual call
        ProductDto productDto1 = productService.create(modelMapper.map(product, ProductDto.class));

        System.out.println(productDto1.getTitle());

        //Assert
        Assertions.assertNotNull(productDto1);
        Assertions.assertEquals("This is iphone Mobile",productDto1.getTitle());


    }

    @Test
    void updateTest() {

    String productId="ASDFG";

        productDto=ProductDto.builder()
                .title("This is Nokia Mobile")
                .description("This mobile is updated in product Service")
                .price(20000)
                .addedDate(new Date())
                .quantity(1)
                .live(true)
                .stock(false)
                .build();

        //Arrange
        Mockito.when(productRepo.findById(Mockito.anyString())).thenReturn(Optional.of(product));
        Mockito.when(productRepo.save(Mockito.any())).thenReturn(product);

        //Actual Call
        ProductDto updateProduct = productService.update(productDto, productId);
        System.out.println(updateProduct.getTitle());
        System.out.println(updateProduct.getPrice());
        //Assert
        Assertions.assertNotNull(productDto);
        Assertions.assertEquals(productDto.getTitle(),updateProduct.getTitle());


    }
}