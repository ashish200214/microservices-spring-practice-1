package com.example.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.order.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders,Long> {

    public List<Orders> findByUserId(Long user_id);
    
    
}
