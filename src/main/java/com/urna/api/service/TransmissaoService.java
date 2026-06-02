package com.urna.api.service;

import com.urna.api.repository.EleicaoRepository;
import com.urna.api.repository.UrnaRepository;
import com.urna.api.service.ApuracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.urna.api.dto.*;

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

        var eleicao = eleicaoRepository
                .findById(dto.getEleicaoId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Eleição não encontrada"));

        var urna = urnaRepository
                .findById(dto.getUrnaId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Urna não encontrada"));

        dto.getVotos().forEach(voto -> {

            apuracaoService.somarVotos(
                    voto.getCandidatoId(),
                    dto.getEleicaoId(),
                    voto.getQuantidade()
            );

        });

    }
}