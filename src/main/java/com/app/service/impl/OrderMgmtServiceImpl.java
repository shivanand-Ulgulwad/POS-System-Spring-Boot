package com.app.service.impl;

import com.app.dto.CreateOrderRequestDTO;
import com.app.dto.OrderItemRequest;
import com.app.dto.OrderItemResponse;
import com.app.dto.OrderResponse;
import com.app.entity.*;
import com.app.exception.BranchNotFoundException;
import com.app.exception.InvalidQuantityException;
import com.app.exception.ProductNotFoundException;
import com.app.repository.BranchRepository;
import com.app.repository.OrderRepository;
import com.app.repository.ProductRepository;
import com.app.repository.UserRepository;
import com.app.service.OrderMgmtService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderMgmtServiceImpl implements OrderMgmtService {

    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;
    private final InventoryMgmtServiceImpl inventoryService;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public OrderResponse createOrder(CreateOrderRequestDTO request) {
        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

     Branch existing = branchRepository.findById(request.getBranchId())
             .orElseThrow(()-> new BranchNotFoundException("Branch Not found"));

        Order order = new Order();
        order.setBranch(existing);
        order.setStatus(Status.CREATED);

       AppUser user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(()-> new RuntimeException("User not found"));

        order.setUser(user);


        if (  request.getItems() == null || request.getItems().isEmpty() ) {

            throw  new IllegalArgumentException("can't access empty product");

        }else {

            for (OrderItemRequest item : request.getItems()) {
                Product product = productRepository.findById(item.getProductId())
                        .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

                if (item.getQuantity() > 0) {
                    Inventory inventory = new Inventory();
                    inventory.setQuantity(item.getQuantity());
                    inventory.setBranch(order.getBranch());
                    inventory.setProduct(product);

                    inventoryService.reduceStock(inventory);

                    BigDecimal price =  product.getPrice();
                    BigDecimal quantity = BigDecimal.valueOf(item.getQuantity());
                    BigDecimal subtotal = price.multiply(quantity);

                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(product);
                    orderItem.setQuantity(item.getQuantity());
                    orderItem.setPrice(price);
                    orderItem.setSubtotal(subtotal);

                    orderItems.add(orderItem);
                    orderItem.setOrder(order);

                    total = total.add(subtotal);


                } else {
                    throw new InvalidQuantityException("Quantity should be > 0");
                }
            }
            order.setItems(orderItems);
            order.setTotalAmount(total);
            orderRepository.save(order);



        }

        List<OrderItemResponse> responseItems = orderItems.stream()
                .map(item ->  OrderItemResponse.builder()
                        .productName(item.getProduct().getProductName())
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .subtotal(item.getSubtotal())
                        .build())
                .toList();

        return OrderResponse.builder()
                .orderId(order.getId())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus().name())
                .items(responseItems)   // ✅ ADD THIS
                .build();
    }
}
