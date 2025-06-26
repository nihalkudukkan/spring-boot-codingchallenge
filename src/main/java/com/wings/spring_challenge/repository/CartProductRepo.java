package com.wings.spring_challenge.repository;

import com.wings.spring_challenge.model.CartProduct;
import com.wings.spring_challenge.model.Product;
import com.wings.spring_challenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartProductRepo extends JpaRepository<CartProduct, Integer> {
    Optional<CartProduct> findByCartUserAndProduct(User user, Product product);
}
