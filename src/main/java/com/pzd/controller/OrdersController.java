package com.pzd.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pzd.DTO.CartDTO;
import com.pzd.DTO.OrderDTO;
import com.pzd.DTO.ProductDTO;
import com.pzd.entities.Orders;
import com.pzd.security.CustomUser;
import com.pzd.service.CartService;
import com.pzd.service.OrderDetailsService;
import com.pzd.service.OrderService;
import com.pzd.service.UserService;
import com.pzd.serviceImpl.CartServiceImpl;

@RestController
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private CartServiceImpl cartServiceImpl;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderDetailsService orderDetailsService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private IdGenerator idGenerator;

	@RequestMapping("/placeOrder")
	public ModelAndView placeOrder(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("Thankyou");

		int userId = ((Integer) request.getSession().getAttribute("userId")).intValue();

//		insert items into the order and orderdetails
//		get items from cart 
//		insert into order details and order at the same time

		ArrayList<CartDTO> cartItems = cartServiceImpl.getAllCartItemsOfUser(userId);

//		Orders order = new Orders();
//		order.setId(idGenerator.getNextId());
//		order.setCompleted(false);
//
//		Timestamp currentDate = new Timestamp(System.currentTimeMillis());
//		order.setDate(currentDate);
		
		OrderDTO orderDTO = orderService.getOrderPojo();
		orderDTO.setId(idGenerator.getNextId());
		orderDTO.setUser(cartItems.get(0).getUser());
		float totalPrice = 0;

		for (CartDTO objects : cartItems) {
			totalPrice = totalPrice + (objects.getProduct().getpPrice() * objects.getProductQuatity());
		}
		orderDTO.setPrice(totalPrice);
		orderService.insertOrder(orderDTO);
		orderDetailsService.insertOrderDetails(cartItems, orderDTO);
		
//		for (CartDTO cartDTO : cartItems) {
//
//			orderDetailsService.insertOrderDetails(orderDTO, cartDTO.getProduct(), cartDTO.getProductQuatity());
//
//		}
		

//		delete items from cart	
		cartServiceImpl.deleteProductFromCartByUserId(userId);
		return mv;
	}
	@RequestMapping("/getOrderPage")
	@ResponseBody
	public ModelAndView getOrderPage(HttpServletRequest request) {
		
		return new ModelAndView("Order");
		
	}
	
	@RequestMapping("/getPendingOrders")
	@ResponseBody
	public ArrayList<HashMap<String, String>> getPendingOrders(HttpServletRequest request) {
		
		return orderService.getAllPendingOrders();
		
	}
	
	@RequestMapping("/getOrderDetails")
	@ResponseBody
	public ArrayList<HashMap<String, String>> getOrderDetails(
			@RequestBody Map<String, Object> payload,
			HttpServletRequest request) {
		
		long orderId = Long.parseLong(payload.get("orderId").toString());

		return orderDetailsService.getOrderDetailsByOrderId(orderId);
		
	}
	
	

}
