package com.example.cartservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cartservice.entities.Cart;
import com.example.cartservice.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart saveCart(Cart cart) {
        // TODO Auto-generated method stub
        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAllCarts() {
        // TODO Auto-generated method stub
            return cartRepository.findAll();
    }

    @Override
    public void deleteCart(Long id) {
        // TODO Auto-generated method stub
         cartRepository.deleteById(id);
    }

    @Override
    public Cart getCartByid(Long id) {
        // TODO Auto-generated method stub
        return cartRepository.getReferenceById(id);
    }

    @Override
    public void deleteAllCarts() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'deleteAllCarts'");
         cartRepository.deleteAll();
    }
    
}
