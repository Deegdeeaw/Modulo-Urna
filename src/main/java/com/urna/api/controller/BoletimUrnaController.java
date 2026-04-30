package com.urna.api.controller;

import com.urna.api.model.BoletimUrna;
import com.urna.api.service.BoletimUrnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boletins")
public class BoletimUrnaController {

    @Autowired
    private BoletimUrnaService service;

    @GetMapping
    public ResponseEntity<List<BoletimUrna>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/eleicao/{eleicaoId}")
    public ResponseEntity<List<BoletimUrna>> porEleicao(@PathVariable Long eleicaoId) {
        return ResponseEntity.ok(service.listarPorEleicao(eleicaoId));
    }

    @GetMapping("/urna/{urnaId}")
    public ResponseEntity<List<BoletimUrna>> porUrna(@PathVariable Long urnaId) {
        return ResponseEntity.ok(service.listarPorUrna(urnaId));
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody BoletimUrna boletim) {
        try {
            return ResponseEntity.status(201).body(service.criar(boletim));
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