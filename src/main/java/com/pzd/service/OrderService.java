package com.pzd.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.pzd.DTO.OrderDTO;
import com.pzd.entities.Orders;

public interface OrderService {

	Long getLastOrderId();

	void insertOrder(OrderDTO orderDTO);

	ArrayList<HashMap<String, String>> getAllPendingOrders();

	OrderDTO getOrderPojo();

	void changeStatusToCompleted(long orderId);

}
