package com.wings.spring_challenge.controllers;

import com.wings.spring_challenge.model.Cart;
import com.wings.spring_challenge.model.CartProduct;
import com.wings.spring_challenge.model.Product;
import com.wings.spring_challenge.model.User;
import com.wings.spring_challenge.repository.CartProductRepo;
import com.wings.spring_challenge.repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth/consumer")
public class ConsumerController {
    @Autowired
    CartRepo cartRepo;
    @Autowired
    CartProductRepo cartProductRepo;
    @GetMapping("/get")
    public String get() {
        return "consumer logged in";
    }
    @GetMapping("/cart")
    public Cart getCart() {
        return cartRepo.findByUser(getUser());
    }

    @PostMapping("/cart")
    public ResponseEntity<?> addToCart(@RequestBody Product product) {
        Optional<CartProduct> cartProduct = cartProductRepo.findByCartUserAndProduct(getUser(),product);
        if (cartProduct.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        CartProduct toSave = new CartProduct();
        toSave.setCart(getCart());
        toSave.setQuantity(1);
        toSave.setProduct(product);
        cartProductRepo.saveAndFlush(toSave);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/cart")
    public ResponseEntity<?> editCart(@RequestBody CartProduct cartProduct) {
        Optional<CartProduct> optionalCartProduct = cartProductRepo.findByCartUserAndProduct(getUser(),cartProduct.getProduct());
        if (optionalCartProduct.isEmpty()) {
            cartProduct.setCart(getCart());
            cartProductRepo.saveAndFlush(cartProduct);
            return ResponseEntity.ok().build();
        }
        if (cartProduct.getQuantity()==0) {
            cartProductRepo.deleteById(optionalCartProduct.get().getCpId());
            return ResponseEntity.ok().build();
        }
        CartProduct dbCartProduct = optionalCartProduct.get();
        dbCartProduct.setQuantity(cartProduct.getQuantity());
        cartProductRepo.saveAndFlush(dbCartProduct);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/cart")
    public ResponseEntity<?> deleteProductFromCart(@RequestBody Product product) {
        Optional<CartProduct> optionalCartProduct = cartProductRepo.findByCartUserAndProduct(getUser(),product);
        cartProductRepo.deleteById(optionalCartProduct.get().getCpId());
        return ResponseEntity.ok().build();
    }

    public User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
