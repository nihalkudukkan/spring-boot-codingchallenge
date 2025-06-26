package com.wings.spring_challenge.repository;

import com.wings.spring_challenge.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Integer> {
}
