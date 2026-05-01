package com.urna.api.controller;

import com.urna.api.model.Apuracao;
import com.urna.api.service.ApuracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apuracao")
public class ApuracaoController {

    @Autowired
    private ApuracaoService service;

    @GetMapping
    public ResponseEntity<List<Apuracao>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/resultado/{eleicaoId}")
    public ResponseEntity<List<Apuracao>> resultado(@PathVariable Long eleicaoId) {
        return ResponseEntity.ok(service.listarResultado(eleicaoId));
    }

    @GetMapping
    @RequestMapping("/buscar")
    public ResponseEntity<?> buscar(
            @RequestParam Long candidatoId,
            @RequestParam Long eleicaoId
    ) {
        Apuracao apuracao = service.buscar(candidatoId, eleicaoId);

        if (apuracao == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(apuracao);
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Apuracao apuracao) {
        try {
            return ResponseEntity.status(201).body(service.criar(apuracao));
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