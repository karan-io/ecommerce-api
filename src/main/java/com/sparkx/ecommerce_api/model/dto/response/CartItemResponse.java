package com.sparkx.ecommerce_api.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemResponse {

    private Long id;
    private Integer quantity;
    private Double productPrice;
    private String productTitle;
    private String productModel;
    private String productBrand;
    private Double subTotal;    //quantity*productPrice
}
