package com.urna.api.controller;

import com.urna.api.model.ControleVoto;
import com.urna.api.service.ControleVotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/controle-votos")
public class ControleVotoController {

    @Autowired
    private ControleVotoService service;

    @GetMapping("/{eleitorId}")
    public ResponseEntity<Boolean> verificar(@PathVariable Long eleitorId) {
        return ResponseEntity.ok(service.eleitorJaVotou(eleitorId));
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody ControleVoto controle) {
        try {
            return ResponseEntity.status(201).body(service.registrar(controle));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{eleitorId}")
    public ResponseEntity<Void> remover(@PathVariable Long eleitorId) {
        service.remover(eleitorId);
        return ResponseEntity.noContent().build();
    }
}

