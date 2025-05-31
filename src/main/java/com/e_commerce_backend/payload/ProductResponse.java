package com.e_commerce_backend.payload;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private List<ProductDto> product;
    private Integer pageNumber;
    private Integer pageSize;

    public List<ProductDto> getProduct() {
        return product;
    }

    public void setProduct(List<ProductDto> product) {
        this.product = product;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
