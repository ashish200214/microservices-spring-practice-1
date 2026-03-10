package com.example.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.dto.ProductDTO;
import com.example.order.dto.UserDTO;
import com.example.order.entity.Orders;
import com.example.order.service.OrderService;




@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
@PostMapping("/order-service")
public ResponseEntity<Orders> saveOrder(@RequestBody Orders order) {
    return ResponseEntity.ok(orderService.saveOrder(order));
}
@GetMapping("/order-service/{id}")
public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
    return ResponseEntity.ok(orderService.getOrderByOrderId(id));
}

@GetMapping("/order-service")
public ResponseEntity<List<Orders>> getAllOrder() {
    return ResponseEntity.ok(orderService.getAllOrders());
}

@GetMapping("/order-service/user/{user_id}")
public ResponseEntity< List<Orders>> findOrderByUserId(@PathVariable Long user_id) {
    return ResponseEntity.ok(orderService.findOrderByUserId(user_id));
}

@GetMapping("/order-service/order/{orderId}")
public ResponseEntity<UserDTO> getUserByOrderId(@PathVariable Long orderId) {
    return orderService.getUserByOrderId(orderId);
}

@GetMapping("/order-service/product/{orderId}")
public ResponseEntity<ProductDTO> getProductByOrderId(@PathVariable Long orderId) {
    return orderService.getProductByOrderId(orderId);
}



}
