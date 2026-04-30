package com.urna.api.service;

import com.urna.api.model.Mesario;
import com.urna.api.repository.MesarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesarioService {

    @Autowired
    private MesarioRepository repository;

    public List<Mesario> listarTodos() {
        return repository.findAll();
    }

    public Mesario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mesário não encontrado (" + id + ")"));
    }

    public Mesario salvar(Mesario mesario) {

        if (repository.existsByCpf(mesario.getCpf())) {
            throw new RuntimeException("CPF já cadastrado!");
        }

        return repository.save(mesario);
    }

    public Mesario atualizar(Long id, Mesario atualizado) {

        Mesario existente = buscarPorId(id);

        if (!existente.getCpf().equals(atualizado.getCpf()) &&
                repository.existsByCpf(atualizado.getCpf())) {
            throw new RuntimeException("CPF já existente!");
        }

        existente.setNome(atualizado.getNome());
        existente.setCpf(atualizado.getCpf());
        existente.setSecao(atualizado.getSecao());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}