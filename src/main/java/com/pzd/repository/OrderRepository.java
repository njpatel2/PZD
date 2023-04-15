package com.pzd.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pzd.entities.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

	@Query("SELECT MAX(o.OrderId) FROM Orders o")
	public Long getLastOrderId();

//	@Transactional
//	@Query("FROM orders o WHERE o.isCompleted = :0")
//	public void getAllPendingOrders();
	
	@Transactional
	@Query("FROM Orders o WHERE o.isCompleted = :isCompleted")
	List<Orders> getAllPendingOrders(@Param("isCompleted") boolean isCompleted);

}
