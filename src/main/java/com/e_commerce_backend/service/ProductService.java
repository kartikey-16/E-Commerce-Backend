package com.e_commerce_backend.service;

import com.e_commerce_backend.payload.ProductDto;
import com.e_commerce_backend.payload.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(ProductDto productDto , Integer productId);
    void deleteProduct(Integer productId) throws Exception;

    ProductDto getProductById(Integer productId);
    ProductResponse getAllProduct(Integer pageNumber , Integer pageSize);

    List<ProductDto> searchProduct(String keyword);
}
