package com.sparkx.ecommerce_api.model.dto.response;

import com.sparkx.ecommerce_api.model.WishlistItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishlistResponse {

    private List<WishlistItemResponse> items;
}
