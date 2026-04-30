package com.urna.api.service;

import com.urna.api.model.SecaoEleitoral;
import com.urna.api.repository.SecaoEleitoralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecaoEleitoralService {

    @Autowired
    private SecaoEleitoralRepository repository;

    public List<SecaoEleitoral> listarTodos() {
        return repository.findAll();
    }

    public SecaoEleitoral buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seção não encontrada (" + id + ")"));
    }

    public List<SecaoEleitoral> listarPorZona(Long zonaId) {
        return repository.findByZona_Id(zonaId);
    }

    public SecaoEleitoral salvar(SecaoEleitoral secao) {

        if (secao.getLocal() == null || secao.getLocal().isEmpty()) {
            throw new RuntimeException("Local da seção é obrigatório!");
        }

        return repository.save(secao);
    }

    public SecaoEleitoral atualizar(Long id, SecaoEleitoral atualizada) {

        SecaoEleitoral existente = buscarPorId(id);

        existente.setLocal(atualizada.getLocal());
        existente.setZona(atualizada.getZona());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}