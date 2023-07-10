package com.electronicstore.service;

import ch.qos.logback.core.CoreConstants;
import com.electronicstore.dtos.ProductDto;
import com.electronicstore.entity.Product;
import com.electronicstore.model.PageableResponse;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.Option;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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

    Product product1;

    Product product2;

    List<Product> productList;

    ProductDto productDto;

    @BeforeEach
    public void init() {

        product = Product.builder()
                .title("This is iphone Mobile")
                .description("This mobile provide for 10GB RAM,125GB internal storage,25000 battery ")
                .price(150000)
                .addedDate(new Date())
                .quantity(10)
                .live(true)
                .stock(false)
                .build();

        product1 = Product.builder()
                .title("This is Samsang Mobile")
                .description("This mobile provide for 6GB RAM,64GB internal storage,5000 battery ")
                .price(20000)
                .addedDate(new Date())
                .quantity(5)
                .live(true)
                .stock(false)
                .build();

        product2 = Product.builder()
                .title("This is POCO Mobile")
                .description("This mobile provide for 8GB RAM,125GB internal storage,5500 battery ")
                .price(25999)
                .addedDate(new Date())
                .quantity(6)
                .live(true)
                .stock(false)
                .build();

        productList = Arrays.asList(product, product1, product2);

        productDto = ProductDto.builder()
                .title("This is iphone Mobile")
                .description("This mobile is updated in product Service")
                .price(20000)
                .addedDate(new Date())
                .quantity(1)
                .live(true)
                .stock(false)
                .build();

    }

    //create
    @Test
    void createTest() {

        //Arrange
        Mockito.when(productRepo.save(Mockito.any())).thenReturn(product);

        //Actual call
        ProductDto productDto1 = productService.create(modelMapper.map(product, ProductDto.class));

        System.out.println(productDto1.getTitle());

        //Assert
        Assertions.assertNotNull(productDto1);
        Assertions.assertEquals("This is iphone Mobile", productDto1.getTitle());


    }

    //update

    @Test
    void updateTest() {

        String productId = "ASDFG";


        //Arrange
        Mockito.when(productRepo.findById(Mockito.anyString())).thenReturn(Optional.of(product));
        Mockito.when(productRepo.save(Mockito.any())).thenReturn(product);

        //Actual Call
        ProductDto updateProduct = productService.update(productDto, productId);
        System.out.println(updateProduct.getTitle());
        System.out.println(updateProduct.getPrice());
        //Assert
        Assertions.assertNotNull(productDto);
        Assertions.assertEquals(productDto.getTitle(), updateProduct.getTitle());


    }

    //get All Product

    @Test
    void getAllProductTest() {

        String productId = "ASDF";
        //Arrange

        List<Product> productList = Arrays.asList(product, product1, product2);

        Page<Product> page = new PageImpl<>(productList);

        Mockito.when(productRepo.findAll((Pageable) Mockito.any())).thenReturn(page);

        PageableResponse<ProductDto> allProduct = productService.getAllProduct(1, 2, "Title", "Ascending");

        Assertions.assertEquals(3, allProduct.getContent().size());

    }

    //get Single Product
    @Test
    void getSingelProductTest() {

        String productId = "ADGFR";
        //Arrange
        Mockito.when(productRepo.findById(Mockito.any())).thenReturn(Optional.of(product));
        //Actual call
        ProductDto singelProduct = productService.getSingelProduct(productId);
        System.out.println(singelProduct.getTitle());
        //Assert
        Assertions.assertNotNull(productDto);
        Assertions.assertEquals(product.getTitle(), singelProduct.getTitle(), "Title not match");


    }

    //delete Product
    @Test
    void deleteProductTest() {

        String productId = "ABCDE";
        //Arrange
        Mockito.when(productRepo.findById("ABCDE")).thenReturn(Optional.of(product));
        //Actual call
        productService.deleteProduct(productId);
        Mockito.verify(productRepo, Mockito.times(1)).delete(product);


    }

    //search By Title
    @Test
    void searchByTitleTest() {

        String keywords="ABCD";

        List<Product> productList = Arrays.asList(product, product1, product2);

        Page<Product>page=new PageImpl<>(productList);

        Mockito.when(productRepo.findByTitleContaining(Mockito.anyString(),Mockito.any())).thenReturn(page);

        PageableResponse<ProductDto> productDtoPageableResponse = productService.searchByTitle("This is Mobile", 1, 2, "Title", "Ascending");


        Assertions.assertEquals(3,productDtoPageableResponse.getContent().size());
    }


}