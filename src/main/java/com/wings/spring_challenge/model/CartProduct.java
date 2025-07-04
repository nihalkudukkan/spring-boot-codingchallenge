package com.wings.spring_challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data@NoArgsConstructor@AllArgsConstructor
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cpId;

    @ManyToOne()
    @JsonIgnore
    private Cart cart;

    @OneToOne()
    private Product product;

    private int quantity;
}
