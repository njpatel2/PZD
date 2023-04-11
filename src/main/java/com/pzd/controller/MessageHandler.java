package com.pzd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.pzd.entities.OrderMessage;

@Controller
public class MessageHandler {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@MessageMapping("/admin/alert")
	public void handleWebSocketMessage(OrderMessage message) {
		System.out.println("Received order: " + message.getOrderDetails() + " from user " + message.getUsername());
		messagingTemplate.convertAndSend("/topic/receivedAlert", message);
	}
}
