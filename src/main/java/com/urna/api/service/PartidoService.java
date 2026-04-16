package com.urna.api.service;

import org.springframework.stereotype.Service;
import com.urna.api.model.Partido;
import com.urna.api.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PartidoService {

    @Autowired
    private PartidoRepository repository;

    public List<Partido> listarTodos() {
        return repository.findAll();
    }

    public Partido salvar(Partido partido) {
        if (repository.existsBySigla(partido.getSigla())) {
            throw new RuntimeException("Sigla já cadastrada!");
        }
        if (repository.existsByNumero(partido.getNumero())) {
            throw new RuntimeException("Número de partido já cadastrado!");
        }
        return repository.save(partido);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}