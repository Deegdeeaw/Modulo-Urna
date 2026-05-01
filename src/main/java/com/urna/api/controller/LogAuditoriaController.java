package com.urna.api.controller;

import com.urna.api.model.LogAuditoria;
import com.urna.api.service.LogAuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogAuditoriaController {

    @Autowired
    private LogAuditoriaService service;

    @GetMapping
    public ResponseEntity<List<LogAuditoria>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<LogAuditoria>> porUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.porUsuario(usuarioId));
    }

    @GetMapping("/entidade/{entidade}")
    public ResponseEntity<List<LogAuditoria>> porEntidade(@PathVariable String entidade) {
        return ResponseEntity.ok(service.porEntidade(entidade));
    }

    @GetMapping("/registro")
    public ResponseEntity<List<LogAuditoria>> porRegistro(
            @RequestParam String entidade,
            @RequestParam Long entidadeId
    ) {
        return ResponseEntity.ok(service.porRegistro(entidade, entidadeId));
    }

    // pra realizar inserção manual / testar
    @PostMapping
    public ResponseEntity<LogAuditoria> criar(@RequestBody LogAuditoria log) {
        return ResponseEntity.status(201).body(service.registrar(log));
    }
}