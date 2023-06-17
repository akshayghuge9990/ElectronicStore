package com.electronicstore.repository;

import com.electronicstore.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,String> {

    //search
    Page<Product> findByTitleContaining(String subTitle, Pageable pagable);

    List<Product> findByLiveTrue(Pageable pagable);






}
