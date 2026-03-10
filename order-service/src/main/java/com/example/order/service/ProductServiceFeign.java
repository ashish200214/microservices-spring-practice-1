package com.example.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.order.dto.ProductDTO;

@FeignClient(name = "product-service")
public interface ProductServiceFeign {
    @GetMapping("/product-service/{id}")
public ResponseEntity<ProductDTO> findProductById(@PathVariable Long id);
}
