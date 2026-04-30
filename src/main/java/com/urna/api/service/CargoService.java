package com.urna.api.service;

import com.urna.api.model.Cargo;
import com.urna.api.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService {

    @Autowired
    private CargoRepository repository;

    public List<Cargo> listarTodos() {
        return repository.findAll();
    }

    public Cargo salvar(Cargo cargo) {

        if (repository.existsByNome(cargo.getNome())) {
            throw new RuntimeException("Cargo já cadastrado!");
        }

        return repository.save(cargo);
    }

    public Cargo atualizar(Long id, Cargo cargoAtualizado) {

        Cargo existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cargo não encontrado (" + id + ")"));

        if (!existente.getNome().equals(cargoAtualizado.getNome()) &&
                repository.existsByNome(cargoAtualizado.getNome())) {
            throw new RuntimeException("Nome já existente!");
        }

        existente.setNome(cargoAtualizado.getNome());
        existente.setQuantidadeVagas(cargoAtualizado.getQuantidadeVagas());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}