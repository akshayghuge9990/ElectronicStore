package com.electronicstore.serviceI;

import com.electronicstore.dtos.ProductDto;

import java.util.List;

public interface ProductServiceI {

    //createProduct
    ProductDto create(ProductDto productDto);

    //updateProduct

    ProductDto update(ProductDto productDto, String productId);

    //getAllProduct

    List<ProductDto> getAllProduct();

    //getSingleProduct

    ProductDto getSingelProduct(String productId);


    //delete Product

     void deleteProduct(String productId);

    //get all :live

    List<ProductDto> getAllLive();

    //search product

    List<ProductDto> searchByTitle(String subTitle);

    //other methods


}
