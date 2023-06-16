package com.electronicstore.repository;

import com.electronicstore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,String> {

    //search
    List<Product> findByTitleContaining(String subTitle);

    List<Product> findByLiveTrue();

    //othermethod
    //custom finder methods
    //query method




}
