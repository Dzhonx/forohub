package com.forohub;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerarPassword {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordEncriptada = encoder.encode("123456");
        System.out.println(passwordEncriptada);
    }
}
