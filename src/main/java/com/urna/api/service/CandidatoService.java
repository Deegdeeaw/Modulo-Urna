package com.urna.api.service;

import com.urna.api.model.Candidato;
import com.urna.api.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository repository;

    public List<Candidato> listarTodos() {
        return repository.findAll();
    }

    public Candidato salvar(Candidato candidato) {

        if (candidato.getEleicao() == null || candidato.getEleicao().getId() == null) {
            throw new RuntimeException("Eleição é obrigatória!");
        }

        if (candidato.getCargo() == null || candidato.getCargo().getId() == null) {
            throw new RuntimeException("Cargo é obrigatório!");
        }

        try {
            return repository.save(candidato);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException(
                    "Já existe um candidato com este número para este cargo nesta eleição e UF."
            );
        }
    }

    public Candidato atualizar(Long id, Candidato candidatoAtualizado) {

        Candidato candidatoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato não encontrado (" + id + ")"));

        candidatoExistente.setNome(candidatoAtualizado.getNome());
        candidatoExistente.setNumero(candidatoAtualizado.getNumero());
        candidatoExistente.setPartido(candidatoAtualizado.getPartido());
        candidatoExistente.setCargo(candidatoAtualizado.getCargo());
        candidatoExistente.setUf(candidatoAtualizado.getUf());
        candidatoExistente.setEleicao(candidatoAtualizado.getEleicao());

        try {
            return repository.save(candidatoExistente);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException(
                    "Já existe um candidato com este número para este cargo nesta eleição e UF."
            );
        }
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}