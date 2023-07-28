package com.pzd.serviceImpl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pzd.DTO.OrderDTO;
import com.pzd.controller.IdGenerator;
import com.pzd.entities.Orders;
import com.pzd.repository.OrderRepository;
import com.pzd.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

//	@Autowired
//	private IdGenerator idGenerator;

	@Override
	public Long getLastOrderId() {

		return orderRepository.getLastOrderId();
	}

	@Override
	public void insertOrder(OrderDTO orderDTO) {
		
		Orders order = new Orders();
		BeanUtils.copyProperties(orderDTO, order);

		orderRepository.save(order);
	}

	@Override
	public ArrayList<HashMap<String, String>> getAllPendingOrders() {

		List<Orders> orders = orderRepository.getAllPendingOrders(false);
		ArrayList<OrderDTO> orderDTOList = new ArrayList<>();
		
		ArrayList<HashMap<String, String>> pendingOrders = new ArrayList<>();
		for (Orders order : orders) {
//			OrderDTO orderDTO = new OrderDTO();
			HashMap<String, String> singleOrder = new HashMap<>();
			
			singleOrder.put("id", Long.toString(order.getId()));
			singleOrder.put("username", order.getUser().getName());
			singleOrder.put("price", Float.toString(order.getPrice()));
			singleOrder.put("Change Status", Boolean.toString(order.isCompleted()));
			
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//	        String formattedTimestamp = dateFormat.format(order.getDate());
	        
			singleOrder.put("date", order.getDate());
			
			pendingOrders.add(singleOrder);
//			BeanUtils.copyProperties(order, orderDTO);
//			orderDTOList.add(orderDTO);
			
		}
		return pendingOrders;
	}

	@Override
	public OrderDTO getOrderPojo() {
		OrderDTO orderDTO = new OrderDTO();
//		orderDTO.setId(idGenerator.getNextId());
		orderDTO.setCompleted(false);

		Timestamp currentDate = new Timestamp(System.currentTimeMillis());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm MM-dd-yyyy");
		String formattedTimestamp = dateFormat.format(currentDate);

		orderDTO.setDate(formattedTimestamp);
		return orderDTO;
	}

	@Override
	public void changeStatusToCompleted(long orderId) {
		orderRepository.changeStatusToCompleted(true,orderId);
		
	}

}
