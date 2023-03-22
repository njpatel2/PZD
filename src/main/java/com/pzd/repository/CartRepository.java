package com.pzd.repository;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pzd.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	@Transactional
	@Query("SELECT c.product.pName , c.product.pPhoto, c.product.pPrice , c.productQuatity ,c.product.pId FROM cart c WHERE c.user.id = :userId")
	ArrayList<Object[]> getAllCartItemsOfUser(@Param("userId") int userId);

//	@Modifying
//	@Query("insert into cart (product_p_id, product_quatity, user_id) values (userId, productId, quantity)")
//	void addItemToCart(@Param("userId") int userId, @Param("productId") int productId, @Param("quantity") int quantity);

	@Transactional
	@Query("FROM cart c WHERE c.user.id = :userId and c.product.pId = :pId")
	Cart getSingleProductFromCart(@Param("userId") int userId, @Param("pId") int pId);

	@Transactional
	@Modifying
	@Query("DELETE FROM cart c WHERE c.product.pId = :productId AND c.user.id = :userId")
	void deleteProductFromCart(@Param("productId") int productId, @Param("userId") int userId);

	@Transactional
	@Modifying
	@Query("UPDATE cart c SET c.productQuatity = :quantity WHERE c.user.id = :userId AND c.product.pId = :productId")
	void updateCartProductQuantity(@Param("quantity") int quantity, @Param("userId") int userId,
			@Param("productId") int productId);

}
