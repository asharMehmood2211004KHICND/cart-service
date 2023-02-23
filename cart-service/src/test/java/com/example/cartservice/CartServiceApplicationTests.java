package com.example.cartservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cartservice.entities.Cart;
import com.example.cartservice.repository.CartRepository;
import com.example.cartservice.service.CartServiceImpl;

import static org.mockito.BDDMockito.*;

import java.util.List;


@SpringBootTest
class CartServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock 
	private CartRepository cartRepository;

	@InjectMocks
	private CartServiceImpl cartServiceImpl;

	@Test
	void getAndSetCartID(){
		Cart cut = new Cart();
		Long myId = 1L;
		cut.setId(myId);
		assertEquals(myId, cut.getId());

	}

	// private Long id;

    // @Column(nullable = false)
    // private String name;
    // @Column(nullable = false)
    // private String imageLink;
    

    // @Column(nullable = false)
    // private String quantity;

    // @Column(nullable = false)
    // private String price;
    // @Column(nullable = false)
    // private String totalPrice;

	@Test
	void getAndSetProductName(){
		Cart cut = new Cart();
		String myName = "abc";
		cut.setName(myName);
		assertEquals(myName,cut.getName());
	}

	
	@Test
	void getAndSetImageLink(){
		Cart cut = new Cart();
		String myImageLink = "abc.png";
		cut.setImageLink(myImageLink);
		assertEquals(myImageLink, cut.getImageLink());

	}

	@Test
	void getAndSetQuantity(){
		Cart cut = new Cart();
		String myQuantity = "7";
		cut.setQuantity(myQuantity);
		assertEquals(myQuantity, cut.getQuantity());
	}

	@Test
	void getAndSetPrice(){
		Cart cut = new Cart();
		String myPrice = "100";
		cut.setPrice(myPrice);
		assertEquals(myPrice,cut.getPrice());

	}

	@Test
	void getAndSetTotalPrice(){
		Cart cut = new Cart();
		String myTotalPrice = "1001";
		cut.setTotalPrice(myTotalPrice);
		assertEquals(myTotalPrice,cut.getTotalPrice());

	}

	@Test
	void builderCart(){
		Long myId = 1L;
		String myName = "abc";
		String myImageLink = "abc.png";
		String myQuantity = "7";
		String myPrice = "100";
		String myTotalPrice = "1001";

		Cart cut = Cart.builder().id(myId)
		.name(myName)
		.imageLink(myImageLink)
		.quantity(myQuantity)
		.price(myPrice)
		.totalPrice(myTotalPrice)
		.build();

		assertEquals(myId, cut.getId());
		assertEquals(myName, cut.getName());
		assertEquals(myImageLink, cut.getImageLink());
		assertEquals(myQuantity, cut.getQuantity());
		assertEquals(myPrice, cut.getPrice());
		assertEquals(myTotalPrice, cut.getTotalPrice());
		

	}

	@Test
	void canSaveCart(){
		Long myId = 1L;
		String myName = "abc";
		String myImageLink = "abc.png";
		String myQuantity = "7";
		String myPrice = "100";
		String myTotalPrice = "1001";

		Cart cut = Cart.builder().id(myId)
		.name(myName)
		.imageLink(myImageLink)
		.quantity(myQuantity)
		.price(myPrice)
		.totalPrice(myTotalPrice)
		.build();

		// given(cartRepository.save(cut)).willReturn(cut);
		given(cartRepository.save(cut)).willReturn(cut);
		Cart savedCart = cartServiceImpl.saveCart(cut);
		assertNotNull(savedCart);

	}

	@Test
	void canGetAllCarts(){
		Long myId = 1L;
		String myName = "abc";
		String myImageLink = "abc.png";
		String myQuantity = "7";
		String myPrice = "100";
		String myTotalPrice = "1001";

		Cart cut1 = Cart.builder().id(myId)
		.name(myName)
		.imageLink(myImageLink)
		.quantity(myQuantity)
		.price(myPrice)
		.totalPrice(myTotalPrice)
		.build();

		Long myId2 = 1L;
		String myName2 = "abc";
		String myImageLink2 = "abc.png";
		String myQuantity2 = "7";
		String myPrice2 = "100";
		String myTotalPrice2 = "1001";

		Cart cut2 = Cart.builder().id(myId2)
		.name(myName2)
		.imageLink(myImageLink2)
		.quantity(myQuantity2)
		.price(myPrice2)
		.totalPrice(myTotalPrice2)
		.build();

		given(cartRepository.findAll()).willReturn(List.of(cut1,cut2));
		List<Cart> cartList = cartServiceImpl.getAllCarts();
		assertNotNull(cartList);
		assertEquals(2, cartList.size() );


	}

	@Test
	void canDeleteAllCarts(){
		willDoNothing().given(cartRepository).deleteAll();
		cartServiceImpl.deleteAllCarts();
		verify(cartRepository, times(1)).deleteAll();


	}


	@Test
	void canDeleteACart(){
		Long myId = 1L;
		willDoNothing().given(cartRepository).deleteById(myId);
		cartServiceImpl.deleteCart(myId);
		verify(cartRepository, times(1)).deleteById(myId);
	}


	


}
