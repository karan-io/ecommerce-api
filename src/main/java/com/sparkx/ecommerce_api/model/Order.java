package com.sparkx.ecommerce_api.model;

import com.sparkx.ecommerce_api.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String shippingAddress;

    @PrePersist
    protected void onCreate(){
        orderDate = LocalDateTime.now();
        if(status == null){
            status = OrderStatus.PENDING;
        }
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    @Builder.Default
    private List<OrderItem> Items = new ArrayList<>();

}

