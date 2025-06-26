package com.wings.spring_challenge.configurations;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfiguration {


    public PasswordEncoder passwordEncoder() {
        return null;
    }

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return null;
    }
}
