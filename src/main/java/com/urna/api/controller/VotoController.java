package com.urna.api.controller;

import com.urna.api.model.Voto;
import com.urna.api.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/votos")
public class VotoController {

    @Autowired
    private VotoService service;

    @PostMapping
    public ResponseEntity<?> votar(
            @RequestBody Voto voto,
            @RequestParam Long eleitorId
    ) {
        try {
            return ResponseEntity.status(201)
                    .body(service.votar(voto, eleitorId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}