package com.sparkx.ecommerce_api.service.inf;

import com.sparkx.ecommerce_api.model.dto.request.WishlistItemRequest;
import com.sparkx.ecommerce_api.model.dto.response.WishlistResponse;

public interface IWishlistService {

    String addToWishlist(WishlistItemRequest request);
    WishlistResponse removeFromWishlist(Long id);
    WishlistResponse getWishlist();

}
