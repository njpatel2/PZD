package com.pzd.repository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pzd.entities.Orders;
import com.pzd.entities.Cart;
import com.pzd.entities.OrderDetails;
import com.pzd.entities.Product;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer>{

	
	@Transactional
	@Query("FROM OrderDetails o WHERE o.Order.OrderId = :orderId")
	ArrayList<OrderDetails> getByOrderId(@Param("orderId") long orderId);

//	@Transactional
//	@Query("update OrderDetails set  o.Order.OrderId = :orderId")
//	void changeStatusToCompleted(@Param("orderId") long orderId);

	
//	@Transactional
//	@Query(insert into)
//	public void insertOrderDetails(Orders order, Product product, int productQuatity);
	


}
