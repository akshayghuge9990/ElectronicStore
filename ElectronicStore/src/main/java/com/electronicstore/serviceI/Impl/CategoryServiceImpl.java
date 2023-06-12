package com.electronicstore.serviceI.Impl;

import com.electronicstore.Config.AppConstatnt;
import com.electronicstore.Config.PageableResponse;
import com.electronicstore.dtos.CategoryDto;
import com.electronicstore.entity.Category;
import com.electronicstore.exception.ResourceNotFoundException;
import com.electronicstore.helper.Helper;
import com.electronicstore.repository.CategoryRepo;
import com.electronicstore.serviceI.CategoryServiceI;
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
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryServiceI {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * @apiNote this method is implementation of create Category
     *
     * @param categoryDto
     * @return CategoryDto
     */

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        log.info("Start the creating Category in CategoryServiceImpl: {}",categoryDto);
        String categoryId1 = UUID.randomUUID().toString();

        categoryDto.setCategoryId(categoryId1);

        log.info("Convert the categoryDto To Category in CategoryServiceImpl: {}",categoryDto);
        Category category = this.modelMapper.map(categoryDto, Category.class);
        log.info("Save Category in CategoryServiceImpl: {}",categoryDto);
        Category saveCategory = this.categoryRepo.save(category);

        log.info("Completed the creating Category in CategoryServiceImpl: {}",categoryDto);
        return this.modelMapper.map(saveCategory,CategoryDto.class);
    }

    /**
     * @apiNote this method is implementation of update Category
     *
     * @param categoryDto
     * @param categoryId
     * @return CategoryDto
     */

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, String categoryId) {

        log.info("Start the update Category in CategoryServiceImpl: {}",categoryDto,categoryId);
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(AppConstatnt.EXCEPTION_MESSAGE));
        category.setDescription(categoryDto.getDescription());
        category.setTitle(category.getTitle());
        category.setCoverImage(categoryDto.getCoverImage());

        log.info("updated Category save in CategoryServiceImpl: {}",categoryDto,categoryId);
        Category updateCategory = this.categoryRepo.save(category);
        log.info("Completed the update Category in CategoryServiceImpl: {}",categoryDto,categoryId);
        return this.modelMapper.map(updateCategory,CategoryDto.class);
    }

    /**@apiNote this method is implementation of get Single Category
     *
     * @param categoryId
     * @return CategoryDto
     */

    @Override
    public CategoryDto getSingleCategory(String categoryId) {

        log.info("Start the get Single  Category in CategoryServiceImpl: {}",categoryId);
        Category category1 = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(AppConstatnt.EXCEPTION_MESSAGE));

        log.info("Completed the get Single  Category in CategoryServiceImpl: {}",categoryId);

        return this.modelMapper.map(category1,CategoryDto.class);
    }

    /**@apiNote this method is implementation of get All Category
     *
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return PageableResponse<CategoryDto>
     */
    @Override
    public PageableResponse<CategoryDto> getAllCatogory(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        log.info("Start the get All Category in CategoryServiceImpl: {}",pageNumber,pageSize,sortBy,sortDir);

        Sort sort =(sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber,pageSize,sort);

        Page<Category> page = this.categoryRepo.findAll(p);

        PageableResponse<CategoryDto> response = Helper.getpagableResponse(page,CategoryDto.class);

        log.info("Completed the get All Category in CategoryServiceImpl: {}",pageNumber,pageSize,sortBy,sortDir);

        return response;
    }

    /**@apiNote this method is implementation of Delete Category
     *
     * @param categoryId
     */

    @Override
    public void deleteCategory(String categoryId) {
        log.info("Start the delete Category in CategoryServiceImpl: {}",categoryId);

        this.categoryRepo.deleteById(categoryId);
        log.info("Start the delete Category in CategoryServiceImpl: {}",categoryId);

    }

    /**
     *
     *@apiNote this method is implementation of search Category
     * @param keyword
     * @return List<CategoryDto>
     */

    @Override
    public List<CategoryDto> searchCategory(String keyword) {

        log.info("Start the search Category in CategoryServiceImpl: {}",keyword);

        List<Category> category = this.categoryRepo.findByTitleContaining(keyword);

        log.info("Convert List of category to categoryDto by using Stream  in CategoryServiceImpl: {}",keyword);
        List<CategoryDto> categoryDto = category.stream().map((cat) -> modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());

        log.info("Completed the search Category in CategoryServiceImpl: {}",keyword);
        return categoryDto;
    }
}
