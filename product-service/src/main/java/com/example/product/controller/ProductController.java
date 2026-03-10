package com.example.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.entity.Product;
import com.example.product.service.ProductService;




@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
@PostMapping("/product-service")
public ResponseEntity<Product> saveProduct(@RequestBody Product Product) {
    return ResponseEntity.ok(productService.saveProduct(Product));
    
}
@GetMapping("/product-service/{id}")
public ResponseEntity<Product> findProductById(@PathVariable Long id) {
    return ResponseEntity.ok(productService.getProductById(id));
}

@GetMapping("/product-service")
public ResponseEntity<List<Product>> getAllProducts() {
    return ResponseEntity.ok(productService.getAllProduct());
}



}
