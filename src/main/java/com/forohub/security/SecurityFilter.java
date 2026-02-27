package com.forohub.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final com.forohub.repository.UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        String method = request.getMethod();

        System.out.println("🔍 FILTER - Path: " + path + ", Method: " + method);

        // IGNORAR LOGIN
        if (path.equals("/auth") || path.equals("/auth/")) {
            System.out.println("Ruta de login detectada - permitiendo sin token");
            chain.doFilter(request, response);
            return;
        }

        System.out.println("Ruta protegida - verificando token");
        String token = recoverToken(request);
        System.out.println("Token: " + (token != null ? token.substring(0, Math.min(20, token.length())) + "..." : "null"));

        if (token != null) {
            try {
                String login = tokenService.getSubject(token);
                System.out.println("Login desde token: " + login);

                var usuario = usuarioRepository.findByLogin(login);

                if (usuario != null) {
                    System.out.println("Usuario encontrado: " + usuario.getUsername());
                    var authentication = new UsernamePasswordAuthenticationToken(
                            usuario,
                            null,
                            usuario.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("Autenticación establecida en contexto");
                } else {
                    System.out.println("Usuario no encontrado en BD");
                }

            } catch (Exception e) {
                System.out.println("Token inválido: " + e.getMessage());
            }
        }

        chain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.replace("Bearer ", "");
        }
        return null;
    }
}