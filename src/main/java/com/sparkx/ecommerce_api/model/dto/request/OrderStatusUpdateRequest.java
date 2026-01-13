package com.sparkx.ecommerce_api.model.dto.request;

import com.sparkx.ecommerce_api.model.enums.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusUpdateRequest {

    @NotNull(message = "ID is required!")
    private Long id;

    @NotNull(message = "Status is required!")
    private OrderStatus status;
}
