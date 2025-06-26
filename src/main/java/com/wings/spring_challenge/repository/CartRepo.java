package com.wings.spring_challenge.repository;

import com.wings.spring_challenge.model.Cart;
import com.wings.spring_challenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Integer> {
    Cart findByUser(User user);
}
