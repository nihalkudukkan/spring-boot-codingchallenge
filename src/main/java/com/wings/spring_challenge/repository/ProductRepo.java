package com.wings.spring_challenge.repository;

import com.wings.spring_challenge.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {
}
