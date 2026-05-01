package com.urna.api.service;

import com.urna.api.model.*;
import com.urna.api.repository.ApuracaoRepository;
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
    private ApuracaoRepository apuracaoRepository;


    public Voto votar(Voto voto, Long eleitorId) {

        if (!eleicaoService.eleicaoEstaAberta(voto.getEleicao().getId())) {
            throw new RuntimeException("Eleição não está aberta!");
        }

        if (controleService.eleitorJaVotou(eleitorId)) {
            throw new RuntimeException("Eleitor já votou!");
        }


        Voto votoSalvo = votoRepository.save(voto);

        ControleVoto controle = new ControleVoto();
        controle.setEleitorId(eleitorId);
        controleService.registrar(controle);

        // atualizar apuração (se não for branco/nulo)
        if (voto.getCandidato() != null) {

            Apuracao apuracao = apuracaoRepository
                    .findByCandidato_IdAndEleicao_Id(
                            voto.getCandidato().getId(),
                            voto.getEleicao().getId()
                    );

            if (apuracao != null) {
                apuracao.setTotalVotos(apuracao.getTotalVotos() + 1);
                apuracaoRepository.save(apuracao);
            }
        }

        return votoSalvo;
    }
}