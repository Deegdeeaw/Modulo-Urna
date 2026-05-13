package com.urna.api.service;

import com.urna.api.repository.EleicaoRepository;
import com.urna.api.repository.UrnaRepository;
import com.urna.api.repository.ApuracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransmissaoService {

    @Autowired
    private EleicaoRepository eleicaoRepository;

    @Autowired
    private UrnaRepository urnaRepository;

    @Autowired
    private ApuracaoService apuracaoService;

    public void processarBoletimUrna(
            BoletimUrnaDTO dto) {

        // 1. Validar eleição
        var eleicao = eleicaoRepository
                .findById(dto.getEleicaoId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Eleição não encontrada"));

        // 2. Validar urna
        var urna = urnaRepository
                .findById(dto.getUrnaId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Urna não encontrada"));

        // 3. Processar votos
        dto.getVotos().forEach(voto -> {

            apuracaoService.somarVotos(
                    voto.getCandidatoId(),
                    voto.getQuantidade()
            );

        });

    }
}