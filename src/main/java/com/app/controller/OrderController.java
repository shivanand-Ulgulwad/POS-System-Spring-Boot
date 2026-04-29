package com.app.controller;

import com.app.dto.CreateOrderRequestDTO;
import com.app.dto.OrderItemRequest;
import com.app.dto.OrderResponse;
import com.app.service.OrderMgmtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderMgmtService orderService;

    @PostMapping("/orders")
    private ResponseEntity<OrderResponse> order(@Valid @RequestBody CreateOrderRequestDTO request){
       OrderResponse response = orderService.createOrder(request);
       return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
