package com.wings.spring_challenge.repository;

import com.wings.spring_challenge.model.Product;
import com.wings.spring_challenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findAllByProductNameContainingIgnoreCaseOrCategoryCategoryNameContainingIgnoreCase(String keyword1, String keyword2);
    List<Product> findAllBySeller(User seller);
    Optional<Product> findBySellerAndProductId(User seller, int id);
}
