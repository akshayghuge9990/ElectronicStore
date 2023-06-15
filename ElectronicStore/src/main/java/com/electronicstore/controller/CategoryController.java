package com.electronicstore.controller;

import com.electronicstore.model.ApiResponse;
import com.electronicstore.model.AppConstatnt;
import com.electronicstore.model.ImageResponse;
import com.electronicstore.model.PageableResponse;
import com.electronicstore.dtos.CategoryDto;
import com.electronicstore.serviceI.CategoryServiceI;
import com.electronicstore.serviceI.FileServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);


    @Autowired
    private CategoryServiceI categoryServiceI;
    @Autowired
    private FileServiceI fileServiceI;
    @Value("${category.profile.image.path}")
    private String ImageUploadPath;


    //createCategory

    /**
     * @param categoryDto
     * @return CategoryDto
     * @author Akshay
     * @apiNote this api is  used to  create Category
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
     * @param categoryDto
     * @param categoryId
     * @return CategoryDto
     * @author Akshay
     * @apiNote this api is  used to  update Category
     */

    @PutMapping("/category/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable String categoryId) {

        log.info("start the api method update category in CategoryController : {}", categoryDto, categoryId);
        CategoryDto updateCategory = this.categoryServiceI.updateCategory(categoryDto, categoryId);

        log.info("Complete the api method update category in CategoryController : {}", categoryDto, categoryId);
        return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);

    }

    //getSingleCategory

    /**
     * @param categoryId
     * @return CategoryDto
     * @author Akshay
     * @apiNote this api is  used to get Single Category
     */

    @GetMapping("/category/{catId}")
    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable("catId") String categoryId) {

        log.info("start the api method get Single Category category in CategoryController : {}", categoryId);
        CategoryDto singleCategory = this.categoryServiceI.getSingleCategory(categoryId);

        log.info("Completed the api method get Single Category category in CategoryController : {}", categoryId);
        return new ResponseEntity<CategoryDto>(singleCategory, HttpStatus.OK);

    }

    //getAllCategory

    /**
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return PageableResponse<CategoryDto>
     * @author Akshay
     * @apiNote this api is  used to get All Category
     */

    @GetMapping("/allCategory")
    public ResponseEntity<PageableResponse<CategoryDto>> getAllCategory(
            @RequestParam(value = "pageNumber", defaultValue = AppConstatnt.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstatnt.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstatnt.SORT_BY1, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstatnt.SORT_DIR, required = false) String sortDir

    ) {
        log.info("start the api method get All Category category in CategoryController : {}", pageNumber, pageSize, sortBy, sortDir);
        PageableResponse<CategoryDto> CatogoryResponse = this.categoryServiceI.getAllCatogory(pageNumber, pageSize, sortBy, sortDir);

        log.info("Completed the api method get All Category category in CategoryController : {}", pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<PageableResponse<CategoryDto>>(CatogoryResponse, HttpStatus.OK);
    }

    //DeleteCategory

    /**
     * @param categoryId
     * @return ApiResponse
     * @author Akshay
     * @apiNote this api is  used to delete Category
     */

    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable String categoryId) {
        log.info("Start the api method delete Category category in CategoryController : {}", categoryId);
        this.categoryServiceI.deleteCategory(categoryId);
        log.info("Completed the api method delete Category category in CategoryController : {}", categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstatnt.CATEGORY_DELETED, false), HttpStatus.OK);
    }

    //search

    /**
     * @param keyword
     * @return List<CategoryDto>
     * @author Akshay
     * @apiNote this api is  used to search Category
     */

    @GetMapping("/category/serach/{keyword}")
    public ResponseEntity<List<CategoryDto>> searchTitle(@PathVariable String keyword) {

         log.info("Start the api method  upload Images in CategoryController : {}",keyword);
        List<CategoryDto> categoryDtoList = this.categoryServiceI.searchCategory(keyword);

        log.info("Completed the api method  upload Images in CategoryController : {}",keyword);
        return new ResponseEntity<List<CategoryDto>>(categoryDtoList, HttpStatus.OK);


    }

    /**
     * @apiNote this api is  used to uploadCategoryImages
     * @author Akshay
     * @param image
     * @param categoryId
     * @return ImageResponse
     * @throws IOException
     */


    @PostMapping("/image/{categoryId}")
    public ResponseEntity<ImageResponse> uploadCategoryImage(@RequestParam("category image") MultipartFile image, @PathVariable String categoryId) throws IOException {

        log.info("Start the api method  upload Images in CategoryController : {}",image,categoryId);
        String imageName = fileServiceI.uploadFile(image, ImageUploadPath);

        CategoryDto category = categoryServiceI.getSingleCategory(categoryId);

        category.setCoverImage(imageName);

        CategoryDto categoryDto = categoryServiceI.updateCategory(category, categoryId);

        ImageResponse imageResponse = ImageResponse.builder().imageName(imageName).success(true).status(HttpStatus.CREATED).build();
        log.info("Completed the api method  upload Images in CategoryController : {}",image,categoryId);
        return new ResponseEntity<>(imageResponse,HttpStatus.CREATED);

    }


}
