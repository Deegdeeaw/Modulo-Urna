package com.urna.api.repository;

import com.urna.api.model.Urna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrnaRepository extends JpaRepository<Urna, Long> {

    boolean existsByNumero(Integer numero);

    List<Urna> findByEleicao_Id(Long eleicaoId);

    List<Urna> findBySecao_Id(Long secaoId);
}