package com.urna.api.service;

import com.urna.api.model.Eleicao;
import com.urna.api.repository.EleicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EleicaoService {

    @Autowired
    private EleicaoRepository repository;

    public List<Eleicao> listarTodos() {
        return repository.findAll();
    }

    public Eleicao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Eleição não encontrada (" + id + ")"));
    }

    public Eleicao criar(Eleicao eleicao) {

        if (eleicao.getDataInicio().isAfter(eleicao.getDataFim())) {
            throw new RuntimeException("Data de início não pode ser depois da data de fim!");
        }

        return repository.save(eleicao);
    }

    public Eleicao atualizar(Long id, Eleicao atualizada) {

        Eleicao existente = buscarPorId(id);

        if (atualizada.getDataInicio().isAfter(atualizada.getDataFim())) {
            throw new RuntimeException("Data de início inválida!");
        }

        existente.setNome(atualizada.getNome());
        existente.setDataInicio(atualizada.getDataInicio());
        existente.setDataFim(atualizada.getDataFim());
        existente.setStatus(atualizada.getStatus());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }


    public boolean eleicaoEstaAberta(Long eleicaoId) {

        Eleicao eleicao = buscarPorId(eleicaoId);

        LocalDateTime agora = LocalDateTime.now();

        return eleicao.getStatus().equalsIgnoreCase("Eleição aberta")
                && agora.isAfter(eleicao.getDataInicio())
                && agora.isBefore(eleicao.getDataFim());
    }
}