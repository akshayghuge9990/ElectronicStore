package com.electronicstore.repository;

import com.electronicstore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category,String> {


   List<Category>  findByTitleContaining(String keyword);

}
