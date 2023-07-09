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

import javax.validation.Valid;


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
    @PostMapping("/product")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        log.info("Start the create product in product controller :{} ", productDto);
        ProductDto productDto1 = this.productServiceI.create(productDto);
        log.info("Completed the create product in product controller :{} ", productDto);
        return new ResponseEntity<ProductDto>(productDto1, HttpStatus.CREATED);

    }

    // update Product

    /**
     *  @Auther Akshay
     * @apiNote this api is  used to update product
     * @param productDto
     * @param productId
     * @return ProductDto
     */
    @PutMapping("/product/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto, @PathVariable String productId) {

        log.info("Start the update product in product controller :{} ", productDto,productId);
        ProductDto update = this.productServiceI.update(productDto, productId);

        log.info("Completed the update product in product controller :{} ", productDto,productId);
        return new ResponseEntity<ProductDto>(update, HttpStatus.OK);


    }
    //get All Product

    /**
     * @Auther Akshay
     *  @apiNote this api is  used to get All product
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return PageableResponse<ProductDto>
     */

    @GetMapping("/All")
    public ResponseEntity<PageableResponse<ProductDto>> getAllProduct(
            @RequestParam(value = "pageNumber", defaultValue = AppConstatnt.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstatnt.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstatnt.SORT_BYPRODUCT, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstatnt.SORT_DIR, required = false) String sortDir
    ) {
        log.info("Start the get All product in product controller :{} ",pageNumber,pageSize,sortBy,sortDir);

        PageableResponse<ProductDto> pagableResponse = this.productServiceI.getAllProduct(pageNumber, pageSize, sortBy, sortDir);

        log.info("Completed the get All product in product controller :{} ",pageNumber,pageSize,sortBy,sortDir);

        return new ResponseEntity<PageableResponse<ProductDto>>(pagableResponse, HttpStatus.OK);
    }

    //get Single Product

    /**
     * @Auther Akshay
     *  @apiNote this api is  used to get Single product
     * @param productId
     * @return ProductDto
     */
    @GetMapping("product/{productId}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable String productId) {
        log.info("Start the get Single product in product controller :{} ",productId);

        ProductDto singelProduct = this.productServiceI.getSingelProduct(productId);

        log.info("Completed the get Single product in product controller :{} ",productId);
        return new ResponseEntity<ProductDto>(singelProduct, HttpStatus.OK);
    }


    // Delete Product

    /**
     * @Auther Akshay
     * @apiNote this api is  used to delete product
     * @param productId
     * @return ApiResponse
     */

    @DeleteMapping("/product/{prodId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("prodId") String productId) {

        log.info("Start the Delete product in product controller :{} ",productId);
        this.productServiceI.deleteProduct(productId);

        log.info("Completed the Delete product in product controller :{} ",productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstatnt.PRODUCT_DELETED, true), HttpStatus.OK);


    }

    //getAllLive

    /**
     * @Auther Akshay
     * @apiNote this api is  used to get All Live product
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return PageableResponse<ProductDto>
     */
    @GetMapping("/product/live")
    public ResponseEntity<PageableResponse<ProductDto>> getAllLive(

            @RequestParam(value = "pageNumber", defaultValue = AppConstatnt.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstatnt.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstatnt.SORT_BYPRODUCT, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstatnt.SORT_DIR, required = false) String sortDir
    ) {

        log.info("Start the get All Live product in product controller :{} ",pageNumber,pageSize,sortBy,sortDir);
        PageableResponse<ProductDto> pagableResponse = this.productServiceI.getAllLive(pageNumber, pageSize, sortBy, sortDir);

        log.info("Cmpleted the get All Live product in product controller :{} ",pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<PageableResponse<ProductDto>>(pagableResponse, HttpStatus.OK);
    }

    //Search

    /**
     * @Auther Akshay
     * @apiNote this api is  used to Search product
     * @param query
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return
     */
    @GetMapping("/search/{query}")
    public ResponseEntity<PageableResponse<ProductDto>> getAllSearch(
            @PathVariable String query,
            @RequestParam(value = "pageNumber", defaultValue = AppConstatnt.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstatnt.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstatnt.SORT_BYPRODUCT, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstatnt.SORT_DIR, required = false) String sortDir
    ) {

        log.info("Start the Search product in product controller :{} ",pageNumber,pageSize,sortBy,sortDir);
        PageableResponse<ProductDto> pagableResponse = this.productServiceI.searchByTitle(query, pageNumber, pageSize, sortBy, sortDir);

        log.info("Completed the Search product in product controller :{} ",pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<PageableResponse<ProductDto>>(pagableResponse, HttpStatus.OK);


    }
}
