package com.electronicstore.serviceI;

import com.electronicstore.dtos.ProductDto;
import com.electronicstore.model.PageableResponse;

import java.util.List;

public interface ProductServiceI {

    //createProduct
    ProductDto create(ProductDto productDto);

    //updateProduct

    ProductDto update(ProductDto productDto, String productId);

    //getAllProduct

    PageableResponse<ProductDto> getAllProduct(int pageNumber,int pageSize,String sortBy,String sortDir);

    //getSingleProduct

    ProductDto getSingelProduct(String productId);


    //delete Product

     void deleteProduct(String productId);

    //get all :live

    PageableResponse<ProductDto> getAllLive(int pageNumber,int pageSize,String sortBy,String sortDir);

    //search product

    PageableResponse<ProductDto> searchByTitle(String subTitle,int pageNumber,int pageSize,String sortBy,String sortDir);

    //other methods


}
