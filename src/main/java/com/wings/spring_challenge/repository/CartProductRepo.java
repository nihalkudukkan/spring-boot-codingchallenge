package com.wings.spring_challenge.repository;

import com.wings.spring_challenge.model.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepo extends JpaRepository<CartProduct, Integer> {
}
