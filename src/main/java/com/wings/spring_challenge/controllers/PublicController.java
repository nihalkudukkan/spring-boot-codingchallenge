package com.wings.spring_challenge.controllers;

import com.wings.spring_challenge.Utils.JwtUtil;
import com.wings.spring_challenge.model.Product;
import com.wings.spring_challenge.model.User;
import com.wings.spring_challenge.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class PublicController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    ProductRepo productRepo;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User credentials) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(token);
            User user = (User) authentication.getPrincipal();
            String jwt = jwtUtil.generateJwt(user);
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/product/search")
    public ResponseEntity<?> search(@RequestParam(name = "keyword") String keyword) {
        List<Product> products = productRepo.findAllByProductNameContainingIgnoreCaseOrCategoryCategoryNameContainingIgnoreCase(keyword,keyword);
        if (products.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
