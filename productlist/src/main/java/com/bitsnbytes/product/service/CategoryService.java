package com.bitsnbytes.product.service;

import com.bitsnbytes.product.dto.CategoryDTO;
import com.bitsnbytes.product.entity.Category;
import com.bitsnbytes.product.exception.CategoryAlreadyExistsException;
import com.bitsnbytes.product.mapper.CategoryMapper;
import com.bitsnbytes.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    //Create Categories
    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryDTO.getName());
        if(optionalCategory.isPresent()){
            throw new CategoryAlreadyExistsException("Category Already Exist with Name: "+categoryDTO.getName());
        }
        Category category = CategoryMapper.toCategoryEntity(categoryDTO);
        category = categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);

    }
    //get All Categories

    public List<CategoryDTO> getAllCategories(){
        return categoryRepository.findAll().stream().map(CategoryMapper::toCategoryDTO).toList();
    }
    // get category by id
    public CategoryDTO getCategoryById(Long id){
        return CategoryMapper.toCategoryDTO(categoryRepository.findById(id).orElseThrow(()->new RuntimeException("Category not found")));
    }

    // delete category
    public String deleteCategoryById(Long id){
        categoryRepository.deleteById(id);
        return "Category Deleted: "+id;
    }

}
