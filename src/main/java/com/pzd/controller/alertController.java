package com.pzd.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class alertController {
	
	@MessageMapping("/admin-alert")
    @SendToUser("/msg/alerts")
    public String handleAdminAlert(String message) {
        return message;
    }

	@MessageMapping("/msg/alerts")
    @SendToUser("/msg/alerts")
    public String handleAdminAlert2(String message) {
        return message;
    }

}
