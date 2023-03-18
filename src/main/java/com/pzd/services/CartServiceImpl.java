package com.pzd.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pzd.DTO.CartDTO;
import com.pzd.DTO.CategoryDTO;
import com.pzd.DTO.ProductDTO;
import com.pzd.entities.Cart;
import com.pzd.entities.Product;
import com.pzd.entities.User;
import com.pzd.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Override
	public ArrayList<CartDTO> getAllCartItemsOfUser(int userId) {

		ArrayList<Cart> CartProducts = cartRepository.getAllCartItemsOfUser(userId);

		ArrayList<CartDTO> products = new ArrayList<>();
		for (Cart cart : CartProducts) {
//			CategoryDTO categorydto = new CategoryDTO(cart.getProduct().getCategory().getCategoryTitle()
//					,cart.getProduct().getCategory().getCategoryDescription());
//			

//			ProductDTO productDto = new ProductDTO(cart.getProduct().getpName(),cart.getProduct().getpDesc(),cart.getProduct().getpPhoto(), 
//					cart.getProduct().getpPrice(),cart.getProduct().getpDiscount(), 1,categorydto);

//			CartDTO cartDTO = new CartDTO(cart.getProduct() ,cart.getProductQuatity());

			// Add the CartDTO object to the list
			products.add(new CartDTO(cart.getProduct(), cart.getProductQuatity()));
		}

		return products;
	}

	@Override
	public void addToCart(int userId, int productId, int quantity) {

		// check if the product is available or not in cart
		Cart product = cartRepository.getSingleProductFromCart(userId, productId);
		if (product != null) {
			cartRepository.updateCartProductQuantity(userId, productId, quantity);
		} else {
			Cart c = new Cart(new User(userId),new Product(productId),quantity);
			cartRepository.save(c);
		}

	}

	@Override
	public float getTotalCartPrice(int userId) {
		ArrayList<Cart> cartItems = cartRepository.getAllCartItemsOfUser(userId);
		float totalPrice = 0;
		for (Cart cart : cartItems) {
			totalPrice = totalPrice + (cart.getProduct().getpPrice() * cart.getProductQuatity());
		}
		return totalPrice;
	}

	@Override
	public void deleteProductFromCart(int userId, int productId) {

		cartRepository.deleteProductFromCart(userId, productId);

	}

	public void updateCartProductQuantity(int userId, int productId, int quantity) {

		cartRepository.updateCartProductQuantity(userId, productId, quantity);

	}

}
