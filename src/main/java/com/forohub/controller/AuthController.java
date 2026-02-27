package com.forohub.controller;

import lombok.*;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;
import com.forohub.security.TokenService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;

    @PostMapping
    public String login(@RequestBody LoginDTO datos) {
        System.out.println("CONTROLLER - Intentando login para: " + datos.login());
        System.out.println("CONTROLLER - Password recibida: " + datos.password());

        try {
            var authToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.password());
            System.out.println("Token de autenticación creado");

            var auth = manager.authenticate(authToken);
            System.out.println("CONTROLLER - Autenticación exitosa para: " + auth.getName());

            String token = tokenService.generarToken(datos.login());
            System.out.println("Token generado: " + token.substring(0, Math.min(20, token.length())) + "...");

            return token;

        } catch (Exception e) {
            System.out.println("CONTROLLER - Error en autenticación: " + e.getClass().getSimpleName());
            System.out.println("Mensaje: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}

record LoginDTO(String login, String password) {}