package com.example.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.order.dto.ProductDTO;
import com.example.order.dto.UserDTO;
import com.example.order.entity.Orders;
import com.example.order.repository.OrderRepository;

@Service
public class OrderService {
@Autowired
private OrderRepository orderRepository;
@Autowired
private UserServiceFeign userServiceFeign;
@Autowired
private ProductServiceFeign productServiceFeign;

public Orders saveOrder(Orders order){
    return orderRepository.save(order);
}
public List<Orders> getAllOrders(){
    return orderRepository.findAll();
}
public Orders getOrderByOrderId(Long id){
    return orderRepository.findById(id).orElseThrow(()->new RuntimeException("Order doesnt exist with id : "+id));

}
public List<Orders> findOrderByUserId(Long user_id) {
    List<Orders> orders = orderRepository.findByUserId(user_id);
    return orders;
}
public ResponseEntity<UserDTO> getUserByOrderId(Long orderId) {
    Orders order = orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("Order with ID : "+orderId+" doesn't exist"));

    Long userId = order.getUserId();
    System.out.println(userServiceFeign.getUserById(userId));
    return userServiceFeign.getUserById(userId);

}
public ResponseEntity<ProductDTO> getProductByOrderId(Long orderId) {
  Orders order = orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("Order with ID "+orderId+" doesn't exist"));
    Long productId = order.getProductId();
    return productServiceFeign.findProductById(productId); 
}

}
