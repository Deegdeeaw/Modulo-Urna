package com.urna.api.controller;

import com.urna.api.model.ZonaEleitoral;
import com.urna.api.service.ZonaEleitoralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zonas")
public class ZonaEleitoralController {

    @Autowired
    private ZonaEleitoralService service;

    @GetMapping
    public ResponseEntity<List<ZonaEleitoral>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ZonaEleitoral zona) {
        try {
            return ResponseEntity.status(201).body(service.salvar(zona));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody ZonaEleitoral zona) {
        try {
            return ResponseEntity.ok(service.atualizar(id, zona));
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