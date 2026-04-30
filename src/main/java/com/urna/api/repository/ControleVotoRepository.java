package com.urna.api.repository;

import com.urna.api.model.ControleVoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControleVotoRepository extends JpaRepository<ControleVoto, Long> {

    boolean existsByEleitorId(Long eleitorId);

}