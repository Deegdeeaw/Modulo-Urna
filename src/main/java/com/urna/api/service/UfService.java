package com.urna.api.service;

import com.urna.api.model.Uf;
import com.urna.api.repository.UfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UfService {

    @Autowired
    private UfRepository repository;

    public List<Uf> listarTodos() {
        return repository.findAll();
    }

    public Uf buscar(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Uf criar(Uf uf) {

        if (repository.existsBySigla(uf.getSigla())) {
            throw new RuntimeException("UF já cadastrada.");
        }

        return repository.save(uf);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}