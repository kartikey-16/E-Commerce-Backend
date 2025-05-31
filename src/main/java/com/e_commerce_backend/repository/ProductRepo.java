package com.e_commerce_backend.repository;

import com.e_commerce_backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product , Integer> {

    @Query("select p  from Product p where p.title like :key")
    List<Product> searchByTitle(@Param("key") String title);
}
