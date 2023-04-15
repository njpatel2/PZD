package com.pzd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pzd.DTO.CartDTO;
import com.pzd.DTO.OrderDTO;
import com.pzd.entities.OrderDetails;
import com.pzd.entities.Orders;
import com.pzd.entities.Product;

public interface OrderDetailsService {

//	void insertOrderDetails(Orders order, Product product, int productQuatity);

	void insertOrderDetails(List<CartDTO> cartDTO, OrderDTO orderDTO);


	ArrayList<HashMap<String, String>> getOrderDetailsByOrderId(long orderId);

}
