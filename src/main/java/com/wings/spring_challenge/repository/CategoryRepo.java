package com.wings.spring_challenge.repository;

import com.wings.spring_challenge.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
