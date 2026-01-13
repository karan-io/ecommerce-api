package com.sparkx.ecommerce_api.model;

import com.sparkx.ecommerce_api.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter        //instead of @Data
@Setter        //
@NoArgsConstructor
@AllArgsConstructor
@Builder       //obj rmba ve ec ya create pannalam..
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist        //save aagurathuku munnadiye ithu call aagum
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate         //update  "  "  "...
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL)
    @Builder.Default
    private List<Product> products = new ArrayList<>();

    @OneToOne(mappedBy = "user" ,cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @Builder.Default
    private List<Order> orders = new ArrayList<>();

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Wishlist wishlist;
}
