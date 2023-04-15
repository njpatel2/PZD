package com.pzd.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.pzd.service.OrderService;

@Component
public class IdGenerator {
    
    private long nextId;
    
    @Autowired
    private OrderService orderService;

    public IdGenerator() {
        // Empty constructor for Spring to create an instance
    }

//    @Autowired
//    public IdGenerator(OrderService orderService) {
//        this.orderService = orderService;
//        Long lastOrderId = orderService.getLastOrderId();
//        if(lastOrderId == null) {
//            nextId = 1;
//        } else {
//            nextId = lastOrderId + 1;
//        }
//    }

    @PostConstruct
    public void init() {
        if (orderService != null) {
            Long lastOrderId = orderService.getLastOrderId();
            if(lastOrderId == null) {
                nextId = 1;
            } else {
                nextId = lastOrderId + 1;
            }
        }
    }

    public synchronized long getNextId() {
        return nextId++;
    }
}

