package com.urna.api.controller;

import com.urna.api.model.Uf;
import com.urna.api.service.UfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ufs")
public class UfController {

    @Autowired
    private UfService service;

    @GetMapping
    public ResponseEntity<List<Uf>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {

        Uf uf = service.buscar(id);

        if (uf == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(uf);
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Uf uf) {

        try {
            return ResponseEntity.status(201).body(service.criar(uf));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}