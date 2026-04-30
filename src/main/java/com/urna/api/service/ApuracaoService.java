package com.urna.api.service;

import com.urna.api.model.Apuracao;
import com.urna.api.repository.ApuracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApuracaoService {

    @Autowired
    private ApuracaoRepository repository;

    public List<Apuracao> listarTodos() {
        return repository.findAll();
    }

    public List<Apuracao> listarResultado(Long eleicaoId) {
        return repository.findByEleicao_IdOrderByTotalVotosDesc(eleicaoId);
    }

    public Apuracao buscar(Long candidatoId, Long eleicaoId) {
        return repository.findByCandidato_IdAndEleicao_Id(candidatoId, eleicaoId);
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

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}