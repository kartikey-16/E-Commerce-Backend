package com.e_commerce_backend.repository;

import com.e_commerce_backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order , Integer> {
}
