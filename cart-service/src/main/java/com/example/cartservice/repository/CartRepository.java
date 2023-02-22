package com.example.cartservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cartservice.entities.Cart;

public interface CartRepository extends JpaRepository<Cart,Long> {
    
}
