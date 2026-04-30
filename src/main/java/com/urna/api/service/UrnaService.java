package com.urna.api.service;

import com.urna.api.model.Urna;
import com.urna.api.repository.UrnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrnaService {

    @Autowired
    private UrnaRepository repository;

    public List<Urna> listarTodos() {
        return repository.findAll();
    }

    public Urna buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Urna não encontrada (" + id + ")"));
    }

    public List<Urna> porEleicao(Long eleicaoId) {
        return repository.findByEleicao_Id(eleicaoId);
    }

    public List<Urna> porSecao(Long secaoId) {
        return repository.findBySecao_Id(secaoId);
    }

    public Urna salvar(Urna urna) {

        if (repository.existsByNumero(urna.getNumero())) {
            throw new RuntimeException("Número de urna já cadastrado!");
        }

        if (urna.getStatus() == null || urna.getStatus().isEmpty()) {
            throw new RuntimeException("Status da urna é obrigatório!");
        }

        return repository.save(urna);
    }

    public Urna atualizar(Long id, Urna atualizada) {

        Urna existente = buscarPorId(id);

        if (!existente.getNumero().equals(atualizada.getNumero()) &&
                repository.existsByNumero(atualizada.getNumero())) {
            throw new RuntimeException("Número de urna já existente!");
        }

        existente.setNumero(atualizada.getNumero());
        existente.setStatus(atualizada.getStatus());
        existente.setSecao(atualizada.getSecao());
        existente.setEleicao(atualizada.getEleicao());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}