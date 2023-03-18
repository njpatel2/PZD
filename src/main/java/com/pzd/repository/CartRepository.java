package com.pzd.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pzd.entities.Cart;
import com.pzd.entities.Category;
import com.pzd.entities.Product;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	@Query("FROM cart c WHERE c.user.id = :userId")
	ArrayList<Cart> getAllCartItemsOfUser(@Param("userId") int userId);

//	@Modifying
//	@Query("INSERT INTO cart(user_id, product_id, product_quantity) VALUES (:userId, :productId, :quantity)")
//	void addItemToCart(@Param("userId") int userId, @Param("productId") int productId, @Param("quantity") int quantity);

	
	@Query("FROM cart c WHERE c.user.id = :userId and c.product.pId = :pId")
	Cart getSingleProductFromCart(@Param("userId") int userId,@Param("pId") int pId);
	
	@Modifying
	@Query("DELETE FROM cart c WHERE c.product.pId = :productId AND c.user.id = :userId")
	void deleteProductFromCart(@Param("productId") int productId, @Param("userId") int userId);

	@Modifying
	@Query("UPDATE cart c SET c.productQuatity = :quantity WHERE c.user.id = :userId AND c.product.pId = :productId")
	void updateCartProductQuantity(@Param("quantity") int quantity, @Param("userId") int userId, @Param("productId") int productId);

}
