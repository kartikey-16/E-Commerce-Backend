package com.e_commerce_backend.controller;

import com.e_commerce_backend.payload.ApiResponse;
import com.e_commerce_backend.payload.AppConstant;
import com.e_commerce_backend.payload.ProductDto;
import com.e_commerce_backend.payload.ProductResponse;
import com.e_commerce_backend.repository.ProductRepo;
import com.e_commerce_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepo productRepo;


    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto)
    {
        ProductDto product = this.productService.createProduct(productDto);
        return  new ResponseEntity<>(product, HttpStatus.CREATED);
    }


    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto,
                                                    @PathVariable Integer productId)
    {
        ProductDto updatedProduct = this.productService.updateProduct(productDto, productId);
        return  new ResponseEntity<>(productDto , HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer productId) throws Exception {
        this.productService.deleteProduct(productId);
//        this.productRepo.deleteById(productId);
        return  new ResponseEntity<ApiResponse>(new ApiResponse("Product deleted Successfully" ,true), HttpStatus.OK);
    }


    @GetMapping("/getProductById/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Integer productId)
    {
        ProductDto productById = this.productService.getProductById(productId);
        return new ResponseEntity<>(productById, HttpStatus.FOUND);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<ProductResponse> getAllProduct(
            @RequestParam(value = "pageNumber" , defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize" , defaultValue = AppConstant.PAGE_SIZE , required = false) Integer pageSize)
    {
        ProductResponse allProduct = this.productService.getAllProduct(pageNumber, pageSize);
        return  new ResponseEntity<>(allProduct, HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<ProductDto>> searchProducts(@PathVariable("keyword") String keyword)
    {
        List<ProductDto> productDtos = this.productService.searchProduct(keyword);
        return new ResponseEntity<>(productDtos ,HttpStatus.OK);
    }

}
