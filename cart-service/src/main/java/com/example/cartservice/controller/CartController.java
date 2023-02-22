package com.example.cartservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cartservice.entities.Cart;
import com.example.cartservice.repository.CartRepository;
import com.example.cartservice.service.CartService;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {
    
    @Autowired
    private CartService cartService;


    @PostMapping("/save")
    public Cart saveCart(@RequestBody Cart cart){

        return cartService.saveCart(cart);

    }

    @GetMapping("/all")
    public  ResponseEntity<List<Cart>> getAllCarts(){
          try{
            List<Cart> allCarts = cartService.getAllCarts();
            return new ResponseEntity<>(allCarts,HttpStatus.OK);
          }  catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);

          }
        // return cartService.getAllCarts();
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable("id") Long cartId) {
        try {
            cartService.deleteCart(cartId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }


    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllCarts() {
        try {
            cartService.deleteAllCarts();
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }
}
