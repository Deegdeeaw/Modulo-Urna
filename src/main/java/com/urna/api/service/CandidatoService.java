package com.urna.api.service;

import org.springframework.stereotype.Service;
import com.urna.api.model.Candidato;
import com.urna.api.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;

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

        boolean numeroExiste = repository.existsByNumeroAndEleicao_Id(
                candidato.getNumero(),
                candidato.getEleicao().getId()
        );

        if (numeroExiste) {
            throw new RuntimeException("Número já cadastrado para esta eleição!");
        }

        return repository.save(candidato);
    }

    public Candidato atualizar(Long id, Candidato candidatoAtualizado) {

        Candidato candidatoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato não encontrado (" + id + ")"));

        boolean mudouNumeroOuEleicao =
                !candidatoExistente.getNumero().equals(candidatoAtualizado.getNumero()) ||
                        !candidatoExistente.getEleicao().getId().equals(candidatoAtualizado.getEleicao().getId());

        if (mudouNumeroOuEleicao) {
            boolean numeroExiste = repository.existsByNumeroAndEleicao_Id(
                    candidatoAtualizado.getNumero(),
                    candidatoAtualizado.getEleicao().getId()
            );

            if (numeroExiste) {
                throw new RuntimeException("Número já cadastrado para esta eleição!");
            }
        }

        candidatoExistente.setNome(candidatoAtualizado.getNome());
        candidatoExistente.setNumero(candidatoAtualizado.getNumero());
        candidatoExistente.setPartido(candidatoAtualizado.getPartido());
        candidatoExistente.setCargo(candidatoAtualizado.getCargo());
        candidatoExistente.setEleicao(candidatoAtualizado.getEleicao());

        return repository.save(candidatoExistente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}