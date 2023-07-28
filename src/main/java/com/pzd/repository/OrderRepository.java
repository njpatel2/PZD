package com.pzd.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
	@Query("FROM Orders o WHERE o.isCompleted = :isCompleted order by o.id desc")
	List<Orders> getAllPendingOrders(@Param("isCompleted") boolean isCompleted);

//	@Transactional
//	@Query("update Orders set isCompleted = 1 where OrderId=:orderId")
//	public void changeStatusToCompleted(@Param("orderId") long orderId);

//	@Modifying
//	@Query("UPDATE Orders o SET o.isCompleted = :1 WHERE o.orderId = :orderId")
//	public int changeStatusToCompleted(@Param("orderId") long orderId);

	@Transactional
	@Modifying
	@Query("UPDATE Orders o SET o.isCompleted = :isCompleted WHERE o.OrderId = :orderId")
	public int changeStatusToCompleted(@Param("isCompleted") boolean isCompleted, @Param("orderId") long orderId);

}
