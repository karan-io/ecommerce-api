package com.sparkx.ecommerce_api.service.inf;

import com.sparkx.ecommerce_api.model.dto.request.CartItemRequest;
import com.sparkx.ecommerce_api.model.dto.response.CartResponse;

public interface ICartService {

    String addToCart(CartItemRequest request);
    CartResponse updateCart(CartItemRequest request);
    CartResponse removeFromCart(Long cartItemId);
    CartResponse getCart();
}
