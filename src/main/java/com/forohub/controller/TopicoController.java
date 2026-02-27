package com.forohub.controller;

import com.forohub.domain.topico.Topico;
import com.forohub.repository.TopicoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class TopicoController {

    private final TopicoRepository repository;

    // LISTAR todos los tópicos (con paginación opcional)
    @GetMapping
    public ResponseEntity<Page<Topico>> listar(
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC)
            Pageable paginacion) {
        return ResponseEntity.ok(repository.findAll(paginacion));
    }

    // CREAR un nuevo tópico (con validación de duplicados)
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody @Valid Topico t, UriComponentsBuilder uriBuilder) {

        // Verificar si ya existe un tópico con el mismo título y mensaje
        Optional<Topico> topicoExistente = repository.findByTituloAndMensaje(t.getTitulo(), t.getMensaje());

        if (topicoExistente.isPresent()) {
            return ResponseEntity.badRequest()
                    .body("Ya existe un tópico con el mismo título y mensaje");
        }

        // Configurar campos automáticos
        t.setFechaCreacion(LocalDateTime.now());
        t.setStatus("ABIERTO");

        // Guardar el tópico
        Topico topicoGuardado = repository.save(t);

        // Construir URI para el recurso creado
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topicoGuardado.getId()).toUri();

        return ResponseEntity.created(uri).body(topicoGuardado);
    }

    // DETALLAR un tópico específico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Topico> detallar(@PathVariable Long id) {
        Optional<Topico> topico = repository.findById(id);

        if (topico.isPresent()) {
            return ResponseEntity.ok(topico.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ACTUALIZAR un tópico existente
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody @Valid Topico topicoActualizado) {

        Optional<Topico> topicoExistente = repository.findById(id);

        if (topicoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Verificar duplicados (excepto el mismo tópico)
        Optional<Topico> duplicado = repository.findByTituloAndMensaje(
                topicoActualizado.getTitulo(),
                topicoActualizado.getMensaje());

        if (duplicado.isPresent() && !duplicado.get().getId().equals(id)) {
            return ResponseEntity.badRequest()
                    .body("Ya existe otro tópico con el mismo título y mensaje");
        }

        // Actualizar campos
        Topico topico = topicoExistente.get();
        topico.setTitulo(topicoActualizado.getTitulo());
        topico.setMensaje(topicoActualizado.getMensaje());
        topico.setAutor(topicoActualizado.getAutor());
        topico.setCurso(topicoActualizado.getCurso());
        // No actualizamos fechaCreacion ni status a menos que sea necesario

        Topico topicoActualizadoGuardado = repository.save(topico);

        return ResponseEntity.ok(topicoActualizadoGuardado);
    }

    // ELIMINAR un tópico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // LISTAR con filtros opcionales (por curso y año)
    @GetMapping("/buscar")
    public ResponseEntity<Page<Topico>> buscarPorFiltros(
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer anio,
            @PageableDefault(size = 10) Pageable paginacion) {

        if (curso != null && anio != null) {
            return ResponseEntity.ok(repository.findByCursoAndYear(curso, anio, paginacion));
        } else if (curso != null) {
            return ResponseEntity.ok(repository.findByCurso(curso, paginacion));
        } else if (anio != null) {
            return ResponseEntity.ok(repository.findByYear(anio, paginacion));
        } else {
            return ResponseEntity.ok(repository.findAll(paginacion));
        }
    }
}