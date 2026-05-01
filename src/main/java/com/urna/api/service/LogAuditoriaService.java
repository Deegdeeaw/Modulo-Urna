package com.urna.api.service;

import com.urna.api.model.LogAuditoria;
import com.urna.api.repository.LogAuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogAuditoriaService {

    @Autowired
    private LogAuditoriaRepository repository;

    public List<LogAuditoria> listarTodos() {
        return repository.findAll();
    }

    public List<LogAuditoria> porUsuario(Long usuarioId) {
        return repository.findByUsuario_Id(usuarioId);
    }

    public List<LogAuditoria> porEntidade(String entidade) {
        return repository.findByEntidade(entidade);
    }

    public List<LogAuditoria> porRegistro(String entidade, Long entidadeId) {
        return repository.findByEntidadeAndEntidadeId(entidade, entidadeId);
    }

    public LogAuditoria registrar(LogAuditoria log) {
        return repository.save(log);
    }
}