package com.urna.api.controller;

import com.urna.api.model.Eleicao;
import com.urna.api.service.EleicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eleicoes")
public class EleicaoController {

    @Autowired
    private EleicaoService service;

    @GetMapping
    public ResponseEntity<List<Eleicao>> listar() {
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
    public ResponseEntity<?> criar(@RequestBody Eleicao eleicao) {
        try {
            return ResponseEntity.status(201).body(service.criar(eleicao));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Eleicao eleicao) {
        try {
            return ResponseEntity.ok(service.atualizar(id, eleicao));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // verifica se tem eleição em aberto
    @GetMapping("/{id}/aberta")
    public ResponseEntity<Boolean> estaAberta(@PathVariable Long id) {
        return ResponseEntity.ok(service.eleicaoEstaAberta(id));
    }
}