package com.wings.spring_challenge.controllers;

import com.wings.spring_challenge.model.Product;
import com.wings.spring_challenge.model.User;
import com.wings.spring_challenge.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/seller")
public class SellerController {
    @Autowired
    ProductRepo productRepo;

    @GetMapping("/get")
    public String get() {
        return "seller logged in";
    }
    @GetMapping("/product")
    public ResponseEntity<?> getAllProduct() {
        return new ResponseEntity<>(productRepo.findAllBySeller(getUser()), HttpStatus.OK);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProductWithId(@PathVariable int id) {
        Optional<Product> optionalProduct = productRepo.findBySellerAndProductId(getUser(),id);
        if (optionalProduct.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK);
    }
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        product.setSeller(getUser());
        Product saved = productRepo.saveAndFlush(product);
        URI location = URI.create("http://localhost:8080/api/auth/seller/product/" + product.getProductId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/product")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        Optional<Product> optionalProduct = productRepo.findBySellerAndProductId(getUser(),product.getProductId());
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        product.setSeller(getUser());
        productRepo.saveAndFlush(product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        Optional<Product> optionalProduct = productRepo.findBySellerAndProductId(getUser(), id);
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        productRepo.deleteById(optionalProduct.get().getProductId());
        return ResponseEntity.ok().build();
    }

    public User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
