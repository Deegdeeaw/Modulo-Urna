package com.urna.api.service;

import com.urna.api.model.Apuracao;
import com.urna.api.model.Candidato;
import com.urna.api.model.Eleicao;
import com.urna.api.repository.ApuracaoRepository;
import com.urna.api.repository.CandidatoRepository;
import com.urna.api.repository.EleicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApuracaoService {

    @Autowired
    private ApuracaoRepository repository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private EleicaoRepository eleicaoRepository;

    public List<Apuracao> listarTodos() {
        return repository.findAll();
    }

    public List<Apuracao> listarResultado(Long eleicaoId) {
        return repository.findByEleicao_IdOrderByTotalVotosDesc(eleicaoId);
    }

    public Apuracao buscar(Long candidatoId, Long eleicaoId) {
        return repository.findByCandidato_IdAndEleicao_Id(
                candidatoId,
                eleicaoId
        );
    }

    public Apuracao criar(Apuracao apuracao) {

        boolean existe = repository.existsByCandidato_IdAndEleicao_Id(
                apuracao.getCandidato().getId(),
                apuracao.getEleicao().getId()
        );

        if (existe) {
            throw new RuntimeException("Apuração já existe!");
        }

        if (apuracao.getTotalVotos() == null) {
            apuracao.setTotalVotos(0);
        }

        return repository.save(apuracao);
    }

    public Apuracao somarVotos(
            Long candidatoId,
            Long eleicaoId,
            Integer quantidade
    ) {

        Candidato candidato = candidatoRepository
                .findById(candidatoId)
                .orElseThrow(() ->
                        new RuntimeException("Candidato não encontrado"));

        Eleicao eleicao = eleicaoRepository
                .findById(eleicaoId)
                .orElseThrow(() ->
                        new RuntimeException("Eleição não encontrada"));

        Apuracao apuracao = repository
                .findByCandidato_IdAndEleicao_Id(
                        candidatoId,
                        eleicaoId
                );

        if (apuracao == null) {

            apuracao = new Apuracao();
            apuracao.setCandidato(candidato);
            apuracao.setEleicao(eleicao);
            apuracao.setTotalVotos(0);

        }

        apuracao.setTotalVotos(
                apuracao.getTotalVotos() + quantidade
        );

        return repository.save(apuracao);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}