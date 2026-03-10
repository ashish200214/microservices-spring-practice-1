package com.example.order.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Orders {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private Long userId;
private Long productId;
private Integer quantity;
private LocalDate orderDate;
}
