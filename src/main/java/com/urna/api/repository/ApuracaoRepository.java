package com.urna.api.repository;

import com.urna.api.model.Apuracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApuracaoRepository extends JpaRepository<Apuracao, Long> {
}
