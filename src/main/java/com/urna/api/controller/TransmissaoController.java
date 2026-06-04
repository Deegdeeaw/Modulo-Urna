package com.urna.api.controller;

import com.urna.api.model.TransmissaoController;
import com.urna.api.service.TransmissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transmissao")
public class TransmissaoController {
    @Autowired
    private TransmissaoService transmissaoService;

    @PostMapping("/boletim-urna")
    public ResponseEntity<?> receberBoletim(
            @RequestBody BoletimUrnaDTO dto) {

        transmissaoService.processarBoletimUrna(dto);

        return ResponseEntity.ok(
                "Boletim processado com sucesso"
        );
    }
}
