package com.electronicstore.controller;

import com.electronicstore.dtos.ProductDto;
import com.electronicstore.model.ApiResponse;
import com.electronicstore.model.AppConstatnt;
import com.electronicstore.model.PageableResponse;
import com.electronicstore.serviceI.ProductServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductServiceI productServiceI;

    //create Product

    /**
     * @param productDto
     * @return ProductDto
     * @apiNote this api is  used to create product
     * @Auther Akshay
     */
    @PostMapping("/")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        log.info("Start the create product in product controller :{} ", productDto);
        ProductDto productDto1 = this.productServiceI.create(productDto);
        log.info("Completed the create product in product controller :{} ", productDto);
        return new ResponseEntity<ProductDto>(productDto1, HttpStatus.CREATED);

    }

    // update Product
    @PutMapping("/product/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable String productId) {

        ProductDto update = this.productServiceI.update(productDto, productId);

        return new ResponseEntity<ProductDto>(update, HttpStatus.OK);


    }
    //get All Product

    @GetMapping("/All")
    public ResponseEntity<PageableResponse<ProductDto>> getAllProduct(
            @RequestParam(value = "pageNumber", defaultValue = AppConstatnt.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstatnt.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstatnt.SORT_BYPRODUCT, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstatnt.SORT_DIR, required = false) String sortDir
    ) {

        PageableResponse<ProductDto> pagableResponse = this.productServiceI.getAllProduct(pageNumber, pageSize, sortBy, sortDir);


        return new ResponseEntity<PageableResponse<ProductDto>>(pagableResponse, HttpStatus.OK);
    }

    //get Single Product
    @GetMapping("product/{productId}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable String productId) {

        ProductDto singelProduct = this.productServiceI.getSingelProduct(productId);

        return new ResponseEntity<ProductDto>(singelProduct, HttpStatus.OK);
    }


    // Delete Product

    @DeleteMapping("/product/{prodId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("prodId") String productId) {

        this.productServiceI.deleteProduct(productId);

        return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstatnt.PRODUCT_DELETED, true), HttpStatus.OK);


    }

    //getAllLive
    @GetMapping("/product/live")
    public ResponseEntity<PageableResponse<ProductDto>> getAllLive(

            @RequestParam(value = "pageNumber", defaultValue = AppConstatnt.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstatnt.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstatnt.SORT_BYPRODUCT, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstatnt.SORT_DIR, required = false) String sortDir
    ) {

        PageableResponse<ProductDto> pagableResponse = this.productServiceI.getAllLive(pageNumber, pageSize, sortBy, sortDir);


        return new ResponseEntity<PageableResponse<ProductDto>>(pagableResponse, HttpStatus.OK);
    }

    //Search
    @GetMapping("/search/{query}")
    public ResponseEntity<PageableResponse<ProductDto>> getAllLive(
            @PathVariable String query,
            @RequestParam(value = "pageNumber", defaultValue = AppConstatnt.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstatnt.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstatnt.SORT_BYPRODUCT, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstatnt.SORT_DIR, required = false) String sortDir
    ) {

        PageableResponse<ProductDto> pagableResponse = this.productServiceI.searchByTitle(query, pageNumber, pageSize, sortBy, sortDir);


        return new ResponseEntity<PageableResponse<ProductDto>>(pagableResponse, HttpStatus.OK);


    }
}
