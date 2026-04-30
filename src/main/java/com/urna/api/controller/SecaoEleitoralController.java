package com.urna.api.controller;

import com.urna.api.model.SecaoEleitoral;
import com.urna.api.service.SecaoEleitoralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/secoes")
public class SecaoEleitoralController {

    @Autowired
    private SecaoEleitoralService service;

    @GetMapping
    public ResponseEntity<List<SecaoEleitoral>> listar() {
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

    @GetMapping("/zona/{zonaId}")
    public ResponseEntity<List<SecaoEleitoral>> porZona(@PathVariable Long zonaId) {
        return ResponseEntity.ok(service.listarPorZona(zonaId));
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody SecaoEleitoral secao) {
        try {
            return ResponseEntity.status(201).body(service.salvar(secao));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody SecaoEleitoral secao) {
        try {
            return ResponseEntity.ok(service.atualizar(id, secao));
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