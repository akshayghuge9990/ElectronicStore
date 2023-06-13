package com.electronicstore.controller;

import com.electronicstore.Config.ApiResponse;
import com.electronicstore.Config.AppConstatnt;
import com.electronicstore.Config.PageableResponse;
import com.electronicstore.dtos.CategoryDto;
import com.electronicstore.serviceI.CategoryServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);


    @Autowired
    private CategoryServiceI categoryServiceI;


    //createCategory

    /**
     * @author Akshay
     * @apiNote this api is  used to  create Category
     * @param categoryDto
     * @return CategoryDto
     */

    @PostMapping("/category")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {

        log.info("start the api method create category in CategoryController : {}", categoryDto);
        CategoryDto category = this.categoryServiceI.createCategory(categoryDto);

        log.info("Competed the api method create category in CategoryController : {}", categoryDto);
        return new ResponseEntity<CategoryDto>(category, HttpStatus.CREATED);


    }

    //updateCategory

    /**
     * @author Akshay
     * @apiNote this api is  used to  update Category
     * @param categoryDto
     * @param categoryId
     * @return CategoryDto
     */

    @PutMapping("/category/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable String categoryId) {

        log.info("start the api method update category in CategoryController : {}", categoryDto,categoryId);
        CategoryDto updateCategory = this.categoryServiceI.updateCategory(categoryDto, categoryId);

        log.info("Complete the api method update category in CategoryController : {}", categoryDto,categoryId);
        return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);

    }

    //getSingleCategory

    /**
     * @author Akshay
     * @apiNote this api is  used to get Single Category
     * @param categoryId
     * @return CategoryDto
     */

    @GetMapping("/category/{catId}")
    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable("catId") String categoryId) {

        log.info("start the api method get Single Category category in CategoryController : {}",categoryId);
        CategoryDto singleCategory = this.categoryServiceI.getSingleCategory(categoryId);

        log.info("Completed the api method get Single Category category in CategoryController : {}",categoryId);
        return new ResponseEntity<CategoryDto>(singleCategory, HttpStatus.OK);

    }

    //getAllCategory

    /**
     * @author Akshay
     * @apiNote this api is  used to get All Category
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return PageableResponse<CategoryDto>
     */

    @GetMapping("/allCategory")
    public ResponseEntity<PageableResponse<CategoryDto>> getAllCategory(
            @RequestParam(value = "pageNumber", defaultValue = AppConstatnt.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstatnt.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstatnt.SORT_BY1, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstatnt.SORT_DIR, required = false) String sortDir

    ) {
        log.info("start the api method get All Category category in CategoryController : {}",pageNumber,pageSize,sortBy,sortDir);
        PageableResponse<CategoryDto> CatogoryResponse = this.categoryServiceI.getAllCatogory(pageNumber, pageSize, sortBy, sortDir);

        log.info("Completed the api method get All Category category in CategoryController : {}",pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<PageableResponse<CategoryDto>>(CatogoryResponse, HttpStatus.OK);
    }

    //DeleteCategory

    /**
     * @author Akshay
     * @apiNote this api is  used to delete Category
     * @param categoryId
     * @return ApiResponse
     */

    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable String categoryId) {
        log.info("Start the api method delete Category category in CategoryController : {}",categoryId);
        this.categoryServiceI.deleteCategory(categoryId);
        log.info("Completed the api method delete Category category in CategoryController : {}",categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstatnt.CATEGORY_DELETED, false), HttpStatus.OK);
    }

    //search

    /**
     * @author Akshay
     * @apiNote this api is  used to search Category
     * @param keyword
     * @return List<CategoryDto>
     */

@GetMapping("/category/serach/{keyword}")
    public ResponseEntity<List<CategoryDto>>searchTitle(@PathVariable("keyword") String keyword) {

        List<CategoryDto> categoryDtoList = this.categoryServiceI.searchCategory(keyword);

        return new ResponseEntity<List<CategoryDto>>(categoryDtoList,HttpStatus.OK);


    }


}
