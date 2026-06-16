package com.urna.api.repository;

import com.urna.api.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

    boolean existsByNumeroAndCargo_IdAndEleicao_IdAndUf_Id(
            Integer numero,
            Long cargoId,
            Long eleicaoId,
            Long ufId
    );

}