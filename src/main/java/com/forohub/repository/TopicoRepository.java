package com.forohub.repository;

import com.forohub.domain.topico.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    // Buscar por título y mensaje (para validar duplicados)
    Optional<Topico> findByTituloAndMensaje(String titulo, String mensaje);

    // Buscar por curso con paginación
    Page<Topico> findByCurso(String curso, Pageable pageable);

    // Buscar por año de fecha de creación
    @Query("SELECT t FROM Topico t WHERE YEAR(t.fechaCreacion) = :anio")
    Page<Topico> findByYear(@Param("anio") Integer anio, Pageable pageable);

    // Buscar por curso y año
    @Query("SELECT t FROM Topico t WHERE t.curso = :curso AND YEAR(t.fechaCreacion) = :anio")
    Page<Topico> findByCursoAndYear(@Param("curso") String curso, @Param("anio") Integer anio, Pageable pageable);
}