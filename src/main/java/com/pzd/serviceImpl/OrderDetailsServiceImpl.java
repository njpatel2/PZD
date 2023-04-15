package com.pzd.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pzd.entities.Orders;
import com.pzd.DTO.CartDTO;
import com.pzd.DTO.OrderDTO;
import com.pzd.entities.Cart;
import com.pzd.entities.OrderDetails;
import com.pzd.entities.Product;
import com.pzd.repository.OrderDetailsRepository;
import com.pzd.service.OrderDetailsService;
import com.pzd.service.OrderService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Autowired
	OrderDetailsRepository orderDetailsRepository;

//	@Autowired
//	private OrderDetailsService orderDetailsService;

	@Override
	public void insertOrderDetails(List<CartDTO> cartDTOs, OrderDTO orderDTO) {
		
		List<OrderDetails> orderDetails = new ArrayList<>();
		
		for (CartDTO cartDTO : cartDTOs) {
			Orders order = new Orders();
			BeanUtils.copyProperties(orderDTO, order);
			OrderDetails orderdetails2 = new OrderDetails(order,cartDTO.getProduct(), cartDTO.getProductQuatity());
			
			orderDetails.add(orderdetails2);
		}
		orderDetailsRepository.saveAll(orderDetails);
		
		
	}
//	@Override
//	public void insertOrderDetails(Orders order, Product product, int productQuatity) {
//
//		
//		
//		
//		
//		
//		orderDetailsRepository.save(new OrderDetails(order, product, productQuatity));
//		
//		
////		insertOrderDetails(order,product,productQuatity);
//	}

	@Override
	public ArrayList<HashMap<String, String>> getOrderDetailsByOrderId(long orderId) {

		ArrayList<OrderDetails> orderDetailsList = orderDetailsRepository.getByOrderId(orderId);
		
		ArrayList<HashMap<String, String>> orderProducts =  new ArrayList<>();
		
		for (OrderDetails orderDetails : orderDetailsList) {
			
			HashMap<String, String> orderProduct = new HashMap<>();
			
			orderProduct.put("productID", Integer.toString(orderDetails.getProduct().getpId()) );
			orderProduct.put("quantity", Integer.toString(orderDetails.getProductQuantity()));
			orderProduct.put("productImage", "/images/"+orderDetails.getProduct().getpPhoto());
			orderProduct.put("productName", orderDetails.getProduct().getpName());
			
			orderProducts.add(orderProduct);
			
		}
		return orderProducts;
				
	}

}
