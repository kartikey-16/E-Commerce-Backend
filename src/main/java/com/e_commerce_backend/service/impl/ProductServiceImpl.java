package com.e_commerce_backend.service.impl;

import com.e_commerce_backend.entity.Product;
import com.e_commerce_backend.exception.ResourceNotFoundException;
import com.e_commerce_backend.payload.ProductDto;
import com.e_commerce_backend.payload.ProductResponse;
import com.e_commerce_backend.repository.ProductRepo;
import com.e_commerce_backend.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;




    @Override
    public ProductDto createProduct(ProductDto productDto) {

        Product product = this.dtoToProduct(productDto);
        Product savedProduct = this.productRepo.save(product);
        return this.productToDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Integer productId) {
        Product product = this.productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));
        product.setTitle(productDto.getTitle());
        product.setPrice(product.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());

        Product savedProduct = this.productRepo.save(product);
        return this.productToDto(savedProduct);
    }

    @Override
    public void deleteProduct(Integer productId) throws Exception {
        Product product = this.productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        if (product.getOrders() != null && !product.getOrders().isEmpty()) {
            throw new DataIntegrityViolationException("Cannot delete product. It is associated with existing orders.");
        }
        this.productRepo.delete(product);
    }

    @Override
    public ProductDto getProductById(Integer productId) {
        Product product = this.productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        return this.productToDto(product);
    }

    @Override
    public ProductResponse getAllProduct(Integer pageNumber , Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Product> pageProduct = this.productRepo.findAll(pageable);
        List<Product> all = pageProduct.getContent();
        List<ProductDto> collect = all.stream().map(product -> this.productToDto(product)).collect(Collectors.toList());

        ProductResponse productResponse = new ProductResponse();

        productResponse.setProduct(collect);
        productResponse.setPageNumber(pageNumber);
        productResponse.setPageSize(pageSize);


        return productResponse;
    }

    @Override
    public List<ProductDto> searchProduct(String keyword) {
        List<ProductDto> collect = this.productRepo.searchByTitle("%" + keyword + "%").stream().map(product -> this.productToDto(product)).collect(Collectors.toList());
        return  collect;
    }


    public   ProductDto productToDto(Product product)
    {
        ProductDto productDto = this.modelMapper.map(product, ProductDto.class);
        return productDto;
    }

    public Product dtoToProduct(ProductDto productDto)
    {
        Product product = this.modelMapper.map(productDto, Product.class);
        return product;
    }
}
