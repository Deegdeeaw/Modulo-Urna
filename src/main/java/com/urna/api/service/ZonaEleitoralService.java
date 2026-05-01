package com.urna.api.service;

import com.urna.api.model.ZonaEleitoral;
import com.urna.api.repository.ZonaEleitoralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonaEleitoralService {

    @Autowired
    private ZonaEleitoralRepository repository;

    public List<ZonaEleitoral> listarTodos() {
        return repository.findAll();
    }

    public ZonaEleitoral buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zona não encontrada (" + id + ")"));
    }

    public ZonaEleitoral salvar(ZonaEleitoral zona) {

        if (repository.existsByNumero(zona.getNumero())) {
            throw new RuntimeException("Número da zona já cadastrado!");
        }

        return repository.save(zona);
    }

    public ZonaEleitoral atualizar(Long id, ZonaEleitoral atualizada) {

        ZonaEleitoral existente = buscarPorId(id);

        if (!existente.getNumero().equals(atualizada.getNumero()) &&
                repository.existsByNumero(atualizada.getNumero())) {
            throw new RuntimeException("Número já existente!");
        }

        existente.setNumero(atualizada.getNumero());
        existente.setCidade(atualizada.getCidade());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}