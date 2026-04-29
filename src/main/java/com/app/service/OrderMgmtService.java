package com.app.service;

import com.app.dto.CreateOrderRequestDTO;
import com.app.dto.OrderResponse;

public interface OrderMgmtService {

    public OrderResponse createOrder(CreateOrderRequestDTO request);

}
