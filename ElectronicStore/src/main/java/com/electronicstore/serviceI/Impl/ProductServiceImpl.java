package com.electronicstore.serviceI.Impl;

import com.electronicstore.dtos.ProductDto;
import com.electronicstore.entity.Product;
import com.electronicstore.exception.ResourceNotFoundException;
import com.electronicstore.helper.Helper;
import com.electronicstore.model.PageableResponse;
import com.electronicstore.repository.ProductRepo;
import com.electronicstore.serviceI.ProductServiceI;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductServiceI {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ModelMapper modelMapper;


    /**
     * @param productDto
     * @return ProductDto
     * @author Akshay
     * @apiNote this method is implementation of create Product
     */

    @Override
    public ProductDto create(ProductDto productDto) {

        log.info("Start the  create product in ProductServiceImpl: {}", productDto);
        Product product = modelMapper.map(productDto, Product.class);

        Product saveProduct = productRepo.save(product);

        log.info("Competed the  create product in ProductServiceImpl: {}", productDto);
        return modelMapper.map(saveProduct, ProductDto.class);
    }

    /**
     * @param productDto
     * @param productId
     * @return ProductDto
     * @author Akshay
     * @apiNote this method is implementation of update Product
     */

    @Override
    public ProductDto update(ProductDto productDto, String productId) {

        log.info("Start the  update product in ProductServiceImpl: {}", productDto, productId);
        Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException());

        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setLive(productDto.isLive());
        productDto.setPrice(productDto.getPrice());
        productDto.setQuantity(productDto.getQuantity());
        productDto.setAddedDate(productDto.getAddedDate());
        productDto.setStock(productDto.isStock());
        Product saveProduct = productRepo.save(product);

        log.info("Completed the  update product in ProductServiceImpl: {}", productDto, productId);
        return modelMapper.map(saveProduct, ProductDto.class);
    }

    /**
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return ProductDto
     * @author Akshay
     * @apiNote this method is implementation of get All Product
     */

    @Override
    public PageableResponse<ProductDto> getAllProduct(int pageNumber, int pageSize, String sortBy, String sortDir) {

        log.info("Start the  get All product in ProductServiceImpl: {}", pageNumber, pageSize, sortBy, sortDir);

        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

        Pageable pagable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Product> page = (Page<Product>) productRepo.findAll(pagable);

        log.info("Completed the  get All product in ProductServiceImpl: {}", pageNumber, pageSize, sortBy, sortDir);
        return Helper.getpagableResponse(page, ProductDto.class);
    }

    /**
     * @param productId
     * @return ProductDto
     * @apiNote this method is implementation of get Single Product
     * @author Akshay
     */

    @Override
    public ProductDto getSingelProduct(String productId) {
        log.info("Start the  get Single product in ProductServiceImpl: {}", productId);

        Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException());

        log.info("Completed the  get Single product in ProductServiceImpl: {}", productId);
        return modelMapper.map(product, ProductDto.class);
    }

    /**
     * @param productId
     * @apiNote this method is implementation of delete Product
     * @author Akshay
     */

    @Override
    public void deleteProduct(String productId) {
        log.info("Start the  delete product in ProductServiceImpl: {}", productId);
        Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException());
        log.info("Completed the  delete product in ProductServiceImpl: {}", productId);
        productRepo.delete(product);

    }

    /**
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return ProductDto
     * @apiNote this method is implementation of get All live Product
     * @author Akshay
     */

    @Override
    public PageableResponse<ProductDto> getAllLive(int pageNumber, int pageSize, String sortBy, String sortDir) {

        log.info("Start the  get All Live product in ProductServiceImpl: {}", pageNumber, pageSize, sortBy, sortDir);
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

        Pageable pagable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Product> page = (Page<Product>) productRepo.findByLiveTrue(pagable);


        log.info("Completed the  get All Live product in ProductServiceImpl: {}", pageNumber, pageSize, sortBy, sortDir);

        return Helper.getpagableResponse(page, ProductDto.class);
    }

    /**
     * @param subTitle
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return ProductDto
     * @apiNote this method is implementation of search by Title
     * @author Akshay
     */

    @Override
    public PageableResponse<ProductDto> searchByTitle(String subTitle, int pageNumber, int pageSize, String sortBy, String sortDir) {

        log.info("Start the Search By title in ProductServiceImpl: {}", subTitle, pageNumber, pageSize, sortBy, sortDir);

        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

        Pageable pagable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Product> page = (Page<Product>) productRepo.findByTitleContaining(subTitle,pagable);

        log.info("Completed the Search By title in ProductServiceImpl: {}", subTitle, pageNumber, pageSize, sortBy, sortDir);
        return Helper.getpagableResponse(page,ProductDto.class);
    }
}
