package com.sparkx.ecommerce_api.repository;

import com.sparkx.ecommerce_api.model.Order;
import com.sparkx.ecommerce_api.model.dto.response.OrderResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByUserId(Long id);
}
