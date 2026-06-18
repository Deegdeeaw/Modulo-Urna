package com.urna.api.controller;

import com.urna.api.model.Candidato;
import com.urna.api.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidato")
public class CandidatoController {

    @Autowired
    private CandidatoService service;

    @GetMapping
    public List<Candidato> listar(
            @RequestParam(required = false) Long eleicaoId,
            @RequestParam(required = false) Long ufId) {

        if (eleicaoId != null && ufId != null) {
            return service.listarPorEleicaoEUf(eleicaoId, ufId);
        }

        return service.listarTodos();
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Candidato candidato) {
        try {
            return ResponseEntity.ok(service.salvar(candidato));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody Candidato candidato) {

        try {
            Candidato atualizado = service.atualizar(id, candidato);
            return ResponseEntity.ok(atualizado);
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