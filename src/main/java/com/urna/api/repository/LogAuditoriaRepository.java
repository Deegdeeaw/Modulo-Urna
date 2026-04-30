package com.urna.api.repository;

import com.urna.api.model.LogAuditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogAuditoriaRepository extends JpaRepository<LogAuditoria, Long> {

    List<LogAuditoria> findByEntidade(String entidade);

    List<LogAuditoria> findByUsuario_Id(Long usuarioId);

    List<LogAuditoria> findByEntidadeAndEntidadeId(String entidade, Long entidadeId);
}