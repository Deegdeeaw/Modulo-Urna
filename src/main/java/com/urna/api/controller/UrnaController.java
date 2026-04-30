package com.urna.api.controller;

import com.urna.api.model.Urna;
import com.urna.api.service.UrnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/urnas")
public class UrnaController {

    @Autowired
    private UrnaService service;

    @GetMapping
    public ResponseEntity<List<Urna>> listar() {
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

    @GetMapping("/eleicao/{eleicaoId}")
    public ResponseEntity<List<Urna>> porEleicao(@PathVariable Long eleicaoId) {
        return ResponseEntity.ok(service.porEleicao(eleicaoId));
    }

    @GetMapping("/secao/{secaoId}")
    public ResponseEntity<List<Urna>> porSecao(@PathVariable Long secaoId) {
        return ResponseEntity.ok(service.porSecao(secaoId));
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Urna urna) {
        try {
            return ResponseEntity.status(201).body(service.salvar(urna));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Urna urna) {
        try {
            return ResponseEntity.ok(service.atualizar(id, urna));
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