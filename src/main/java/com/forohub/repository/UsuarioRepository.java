package com.forohub.repository;

import com.forohub.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Este método debe devolver UserDetails, NO Optional
    UserDetails findByLogin(String login);
}