package com.bitsnbytes.product.controller;

import com.bitsnbytes.product.dto.CategoryDTO;
import com.bitsnbytes.product.dto.ProductDTO;
import com.bitsnbytes.product.entity.Product;
import com.bitsnbytes.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name="Product REST API CRUD Operation",
        description = "CREATE READ UPDATE DELETE Operation for Product Rest API"
)
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    // get Product
    @Operation(
            summary = "Fetch all products",
            description = "REST API to fetch all products"
    )
    @GetMapping
    public List<ProductDTO> getProduct(){
        return productService.getAllProducts();
    }

    // get Product By Id
    @Operation(
            summary = "Fetch product by productId",
            description = "REST API to fetch product"
    )
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @Operation(
            summary = "Create product",
            description = "REST API to Create product"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Created"
    )
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    // updateProduct
    @Operation(
            summary = "update product by productId",
            description = "REST API to update productId"
    )
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        return productService.updateProduct(id,productDTO);
    }

    // deleteProduct
    @Operation(
            summary = "Delete product by productId",
            description = "REST API to delete product"
    )

    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }
}
