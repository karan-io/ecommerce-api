package com.sparkx.ecommerce_api.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistItemRequest {

    @NotNull(message = "Product ID is required!")
    private Long ProductId;
}
