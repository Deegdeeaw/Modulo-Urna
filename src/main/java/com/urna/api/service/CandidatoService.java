package com.urna.api.service;

import com.urna.api.model.Candidato;
import com.urna.api.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        Long ufId = candidato.getUf() != null
                ? candidato.getUf().getId()
                : null;

        boolean numeroExiste = repository.existsByNumeroAndCargo_IdAndEleicao_IdAndUf_Id(
                candidato.getNumero(),
                candidato.getCargo().getId(),
                candidato.getEleicao().getId(),
                ufId
        );

        if (numeroExiste) {
            throw new RuntimeException(
                    "Já existe um candidato com este número para este cargo nesta eleição."
            );
        }

        return repository.save(candidato);
    }

    public Candidato atualizar(Long id, Candidato candidatoAtualizado) {

        Candidato candidatoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato não encontrado (" + id + ")"));

        Long ufAtual = candidatoAtualizado.getUf() != null
                ? candidatoAtualizado.getUf().getId()
                : null;

        Long ufExistente = candidatoExistente.getUf() != null
                ? candidatoExistente.getUf().getId()
                : null;

        boolean mudou =
                !candidatoExistente.getNumero().equals(candidatoAtualizado.getNumero())
                        || !candidatoExistente.getCargo().getId().equals(candidatoAtualizado.getCargo().getId())
                        || !candidatoExistente.getEleicao().getId().equals(candidatoAtualizado.getEleicao().getId())
                        || (
                        (ufExistente == null && ufAtual != null)
                                || (ufExistente != null && ufAtual == null)
                                || (ufExistente != null && !ufExistente.equals(ufAtual))
                );

        if (mudou) {

            boolean numeroExiste = repository.existsByNumeroAndCargo_IdAndEleicao_IdAndUf_Id(
                    candidatoAtualizado.getNumero(),
                    candidatoAtualizado.getCargo().getId(),
                    candidatoAtualizado.getEleicao().getId(),
                    ufAtual
            );

            if (numeroExiste) {
                throw new RuntimeException(
                        "Já existe um candidato com este número para este cargo nesta eleição."
                );
            }
        }

        candidatoExistente.setNome(candidatoAtualizado.getNome());
        candidatoExistente.setNumero(candidatoAtualizado.getNumero());
        candidatoExistente.setPartido(candidatoAtualizado.getPartido());
        candidatoExistente.setCargo(candidatoAtualizado.getCargo());
        candidatoExistente.setUf(candidatoAtualizado.getUf());
        candidatoExistente.setEleicao(candidatoAtualizado.getEleicao());

        return repository.save(candidatoExistente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

}