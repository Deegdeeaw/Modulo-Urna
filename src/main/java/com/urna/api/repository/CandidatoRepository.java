package com.urna.api.repository;

import com.urna.api.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

    List<Candidato> findByEleicaoIdAndUfIdOrEleicaoIdAndCargoNome(
            Long eleicaoIdUf,
            Long ufId,
            Long eleicaoIdPresidente,
            String cargo
    );

}