package com.urna.api.service;

import com.urna.api.model.Eleitor;
import com.urna.api.repository.EleitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EleitorService {

    @Autowired
    private EleitorRepository repository;

    public List<Eleitor> listarTodos() {
        return repository.findAll();
    }

    public Eleitor buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Eleitor não encontrado (" + id + ")"));
    }

    public Eleitor salvar(Eleitor eleitor) {

        if (repository.existsByCpf(eleitor.getCpf())) {
            throw new RuntimeException("CPF já cadastrado!");
        }

        if (repository.existsByTitulo(eleitor.getTitulo())) {
            throw new RuntimeException("Título já cadastrado!");
        }

        return repository.save(eleitor);
    }

    public Eleitor atualizar(Long id, Eleitor atualizado) {

        Eleitor existente = buscarPorId(id);

        if (!existente.getCpf().equals(atualizado.getCpf()) &&
                repository.existsByCpf(atualizado.getCpf())) {
            throw new RuntimeException("CPF já existente!");
        }

        if (!existente.getTitulo().equals(atualizado.getTitulo()) &&
                repository.existsByTitulo(atualizado.getTitulo())) {
            throw new RuntimeException("Título já existente!");
        }

        existente.setNome(atualizado.getNome());
        existente.setCpf(atualizado.getCpf());
        existente.setTitulo(atualizado.getTitulo());
        existente.setSecao(atualizado.getSecao());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}