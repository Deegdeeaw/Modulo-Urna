package com.urna.api.service;

import com.urna.api.model.*;
import com.urna.api.repository.CandidatoRepository;
import com.urna.api.repository.EleitorRepository;
import com.urna.api.repository.UrnaRepository;
import com.urna.api.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private EleicaoService eleicaoService;

    @Autowired
    private ControleVotoService controleService;

    @Autowired
    private ApuracaoService apuracaoService;

    @Autowired
    private EleitorRepository eleitorRepository;

    @Autowired
    private UrnaRepository urnaRepository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    public Voto votar(Voto voto, Long eleitorId) {

        if (!eleicaoService.eleicaoEstaAberta(voto.getEleicao().getId())) {
            throw new RuntimeException("Eleição não está aberta!");
        }

        if (controleService.eleitorJaVotou(eleitorId)) {
            throw new RuntimeException("Eleitor já votou!");
        }

        Eleitor eleitor = eleitorRepository.findById(eleitorId)
                .orElseThrow(() ->
                        new RuntimeException("Eleitor não encontrado."));

        Urna urna = urnaRepository.findById(voto.getUrna().getId())
                .orElseThrow(() ->
                        new RuntimeException("Urna não encontrada."));

        if (!urna.getStatus().equalsIgnoreCase("ATIVA")) {
            throw new RuntimeException("Esta urna não está ativa.");
        }

        if (!urna.getEleicao().getId().equals(voto.getEleicao().getId())) {
            throw new RuntimeException("A urna não pertence a esta eleição.");
        }

        if (eleitor.getUf() == null) {
            throw new RuntimeException("Eleitor sem UF cadastrada.");
        }


        if (urna.getSecao().getZona().getUf() == null) {
            throw new RuntimeException("A zona eleitoral da urna não possui UF.");
        }

        // O eleitor só pode votar em urna da própria UF
        if (!urna.getSecao()
                .getZona()
                .getUf()
                .getId()
                .equals(eleitor.getUf().getId())) {

            throw new RuntimeException(
                    "O eleitor não pode votar nesta urna."
            );

        }

        // Se houver candidato (voto válido)
        if (voto.getCandidato() != null) {

            Candidato candidato = candidatoRepository
                    .findById(voto.getCandidato().getId())
                    .orElseThrow(() ->
                            new RuntimeException("Candidato não encontrado."));

            // Verifica se pertence à eleição
            if (!candidato.getEleicao()
                    .getId()
                    .equals(voto.getEleicao().getId())) {

                throw new RuntimeException(
                        "O candidato não pertence a esta eleição."
                );

            }

            // Exceto Presidente, todos precisam possuir UF
            if (!candidato.getCargo()
                    .getNome()
                    .equalsIgnoreCase("Presidente")) {

                if (candidato.getUf() == null) {
                    throw new RuntimeException(
                            "Candidato sem UF cadastrada."
                    );
                }

                // Candidato deve ser da mesma UF do eleitor
                if (!candidato.getUf()
                        .getId()
                        .equals(eleitor.getUf().getId())) {

                    throw new RuntimeException(
                            "Este candidato pertence a outra UF."
                    );

                }

            }

        }


        Voto votoSalvo = votoRepository.save(voto);

        // Marca eleitor como votante
        ControleVoto controle = new ControleVoto();
        controle.setEleitorId(eleitorId);

        controleService.registrar(controle);

        // Atualiza a apuração
        if (voto.getCandidato() != null) {

            apuracaoService.somarVotos(
                    voto.getCandidato().getId(),
                    voto.getEleicao().getId(),
                    1
            );

        }

        return votoSalvo;

    }

}