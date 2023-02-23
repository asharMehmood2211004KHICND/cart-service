package com.example.cartservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.cartservice.entities.Cart;
import com.example.cartservice.service.CartServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.hibernate.annotations.UpdateTimestamp;

import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;

import org.springframework.boot.test.json.JacksonTester;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest
public class MyCartControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartServiceImpl cartService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void postNewProduct() throws Exception {
        Cart cut1 = Cart.builder().id(1L)
                .name("myName")
                .imageLink("myimagelink")
                .quantity("myquantity")
                .price("myprice")
                .totalPrice("mytotalprice")
                .build();

        given(cartService.saveCart(any(Cart.class))).willAnswer((invocation) -> invocation.getArgument(0));
        ResultActions response = mockMvc.perform(post("/cart/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cut1)));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(cut1.getName())));

    }

    @Test
    public void getAllTheProducts() throws Exception{
        Cart cut1 = Cart.builder().id(1L)
        .name("myName")
        .imageLink("myimagelink")
        .quantity("myquantity")
        .price("myprice")
        .totalPrice("mytotalprice")
        .build();

        Cart cut2 = Cart.builder().id(2L)
        .name("myName2")
        .imageLink("myimagelink2")
        .quantity("myquantity2")
        .price("myprice2")
        .totalPrice("mytotalprice2")
        .build();
        
        
        List<Cart> carts = List.of(cut1,cut2);
        given(cartService.getAllCarts()).willReturn(carts);

        ResultActions response = mockMvc.perform(get("/cart/all"));
        
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(carts.size())));
    }    



    @Test
    public void canDeleteAProduct() throws Exception{
        Long cartId = 1L;
        willDoNothing().given(cartService).deleteCart(cartId);

        ResultActions response = mockMvc.perform(delete("/cart/delete/{id}", cartId));

        response.andExpect(status().isOk()).andDo(print());
        verify(cartService,times(1)).deleteCart(cartId);
    }

    @Test
    public void canDeleteAllCarts() throws Exception{
        willDoNothing().given(cartService).deleteAllCarts();
        ResultActions response = mockMvc.perform(delete("/cart/delete/all"));

        response.andExpect(status().isOk()).andDo(print());
        verify(cartService,times(1)).deleteAllCarts();
        
    }

}
