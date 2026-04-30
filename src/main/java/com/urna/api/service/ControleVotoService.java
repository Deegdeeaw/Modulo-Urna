package com.urna.api.service;

import com.urna.api.model.ControleVoto;
import com.urna.api.repository.ControleVotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ControleVotoService {

    @Autowired
    private ControleVotoRepository repository;

    public boolean eleitorJaVotou(Long eleitorId) {
        return repository.existsByEleitorId(eleitorId);
    }

    public ControleVoto registrar(ControleVoto controle) {

        if (repository.existsByEleitorId(controle.getEleitorId())) {
            throw new RuntimeException("Eleitor já votou!");
        }

        return repository.save(controle);
    }

    public void remover(Long eleitorId) {
        repository.deleteById(eleitorId);
    }
}