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

    public Partido atualizar(Long id, Partido partidoAtualizado) {
        Partido partidoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido não encontrado (" + id + ")"));

        if (!partidoExistente.getSigla().equals(partidoAtualizado.getSigla()) &&
                repository.existsBySigla(partidoAtualizado.getSigla())) {
            throw new RuntimeException("Sigla já existente!");
        }

        partidoExistente.setNome(partidoAtualizado.getNome());
        partidoExistente.setSigla(partidoAtualizado.getSigla());
        partidoExistente.setNumero(partidoAtualizado.getNumero());

        return repository.save(partidoExistente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}