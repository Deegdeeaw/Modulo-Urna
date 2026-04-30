package com.urna.api.service;

import com.urna.api.model.BoletimUrna;
import com.urna.api.repository.BoletimUrnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoletimUrnaService {

    @Autowired
    private BoletimUrnaRepository repository;

    public List<BoletimUrna> listarTodos() {
        return repository.findAll();
    }

    public List<BoletimUrna> listarPorEleicao(Long eleicaoId) {
        return repository.findByEleicao_Id(eleicaoId);
    }

    public List<BoletimUrna> listarPorUrna(Long urnaId) {
        return repository.findByUrna_Id(urnaId);
    }

    public BoletimUrna criar(BoletimUrna boletim) {

        if (repository.existsByHash(boletim.getHash())) {
            throw new RuntimeException("Boletim já registrado (hash duplicado)!");
        }

        if (boletim.getDadosJson() == null || boletim.getDadosJson().isEmpty()) {
            throw new RuntimeException("Dados do boletim são obrigatórios!");
        }

        return repository.save(boletim);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}