package com.example.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product.entity.Product;
import com.example.product.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
public Product saveProduct(Product product){
    return productRepository.save(product);
}

public Product getProductById(Long id){
    return productRepository.findById(id).orElseThrow(()->new RuntimeException("Product with id "+id+" doesn't exist..."));
}

public List<Product> getAllProduct(){
    return productRepository.findAll();
}
}
