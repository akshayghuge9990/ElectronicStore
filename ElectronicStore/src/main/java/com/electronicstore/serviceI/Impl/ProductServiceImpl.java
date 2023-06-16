package com.electronicstore.serviceI.Impl;

import com.electronicstore.dtos.ProductDto;
import com.electronicstore.entity.Product;
import com.electronicstore.exception.ResourceNotFoundException;
import com.electronicstore.repository.ProductRepo;
import com.electronicstore.serviceI.ProductServiceI;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

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
     * @author Akshay
     * @apiNote this method is implementation of update Product
     * @param productDto
     * @param productId
     * @return ProductDto
     */

    @Override
    public ProductDto update(ProductDto productDto, String productId) {

        log.info("Start the  update product in ProductServiceImpl: {}", productDto,productId);
        Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException());

        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setLive(productDto.isLive());
        productDto.setPrice(productDto.getPrice());
        productDto.setQuantity(productDto.getQuantity());
        productDto.setAddedDate(productDto.getAddedDate());
        productDto.setStock(productDto.isStock());
        Product saveProduct = productRepo.save(product);

        log.info("Completed the  update product in ProductServiceImpl: {}", productDto,productId);
        return modelMapper.map(saveProduct, ProductDto.class);
    }

    /**
     * @author Akshay
     * @apiNote this method is implementation of update Product
     *
     * @return List<ProductDto>
     */

    @Override
    public List<ProductDto> getAllProduct() {

        log.info("Start the  get All product in ProductServiceImpl: {}");
        List<Product> product = productRepo.findAll();

        List<ProductDto> productDto = product.stream().map((pro) -> this.modelMapper.map(pro, ProductDto.class)).collect(Collectors.toList());
        log.info("Completed the  get All product in ProductServiceImpl: {}");
        return productDto;
    }

    /**
     *
     * @apiNote this method is implementation of get Single Product
     * @author Akshay
     * @param productId
     * @return ProductDto
     */

    @Override
    public ProductDto getSingelProduct(String productId) {
        log.info("Start the  get Single product in ProductServiceImpl: {}",productId);

        Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException());
        Product productsave = productRepo.save(product);

        log.info("Completed the  get Single product in ProductServiceImpl: {}",productId);
        return modelMapper.map(productsave, ProductDto.class);
    }

    /**@apiNote this method is implementation of delete Product
     * @author Akshay
     * @param productId
     */

    @Override
    public void deleteProduct(String productId) {
        log.info("Start the  delete product in ProductServiceImpl: {}",productId);
        Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException());
        log.info("Completed the  delete product in ProductServiceImpl: {}",productId);
        productRepo.delete(product);

    }

    /**@apiNote this method is implementation of get All live Product
     *  @author Akshay
     * @return List<ProductDto>
     */
    @Override
    public List<ProductDto> getAllLive() {

        log.info("Start the  get All Live product in ProductServiceImpl: {}");

        List<Product> product = productRepo.findAll();

        List<ProductDto> productDto = product.stream().map((pro) -> this.modelMapper.map(pro, ProductDto.class)).collect(Collectors.toList());

        log.info("Completed the  get All Live product in ProductServiceImpl: {}");

        return productDto;
    }

    /**@apiNote this method is implementation of search by Title
     * @author Akshay
     * @param subTitle
     * @return List<ProductDto>
     */

    @Override
    public List<ProductDto> searchByTitle(String subTitle) {

        log.info("Start the Search By title in ProductServiceImpl: {}",subTitle);
        List<Product> product = productRepo.findAll();

        List<ProductDto> productDto = product.stream().map((pro) -> this.modelMapper.map(pro, ProductDto.class)).collect(Collectors.toList());

        log.info("Completed the Search By title in ProductServiceImpl: {}",subTitle);
        return productDto;
    }
}
