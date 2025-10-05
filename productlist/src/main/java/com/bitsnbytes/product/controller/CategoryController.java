package com.bitsnbytes.product.controller;

import com.bitsnbytes.product.dto.CategoryDTO;
import com.bitsnbytes.product.entity.Category;
import com.bitsnbytes.product.exception.CategoryAlreadyExistsException;
import com.bitsnbytes.product.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name="Category REST API CRUD Operation",
        description = "CREATE READ UPDATE DELETE Operation for Category Rest API"
)
@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    // get all categories
    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // create categories
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
        try{
            CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(savedCategory);
        } catch (CategoryAlreadyExistsException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }

//        return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }
    // get category by id
    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    // delete category
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteCategoryById(@PathVariable Long id){
        return categoryService.deleteCategoryById(id);
    }
}
