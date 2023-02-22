package com.example.cartservice.service;

import java.util.List;

import com.example.cartservice.entities.Cart;

public interface CartService {
    
    public Cart saveCart(Cart cart);

    public List<Cart> getAllCarts();
    
    public void deleteCart(Long id);

    public Cart getCartByid(Long id);

    public void deleteAllCarts();



}
