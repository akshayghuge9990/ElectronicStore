package com.electronicstore.serviceI.Impl;

import com.electronicstore.dtos.ProductDto;
import com.electronicstore.serviceI.ProductServiceI;

import java.util.List;

public class ProductServiceImpl implements ProductServiceI {
    @Override
    public ProductDto create(ProductDto productDto) {
        return null;
    }

    @Override
    public ProductDto update(ProductDto productDto, String productId) {
        return null;
    }

    @Override
    public List<ProductDto> getAllProduct() {
        return null;
    }

    @Override
    public ProductDto getSingelProduct(String productId) {
        return null;
    }

    @Override
    public void deleteProduct(String productId) {

    }

    @Override
    public List<ProductDto> getAllLive() {
        return null;
    }

    @Override
    public List<ProductDto> searchByTitle(String subTitle) {
        return null;
    }
}
