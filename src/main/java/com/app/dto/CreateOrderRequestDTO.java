package com.app.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequestDTO {
    @NotNull
    private Long branchId;

    @NotEmpty
    private List<OrderItemRequest> items;

}
