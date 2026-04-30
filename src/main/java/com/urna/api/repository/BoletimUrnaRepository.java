package com.urna.api.repository;

import com.urna.api.model.BoletimUrna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoletimUrnaRepository extends JpaRepository<BoletimUrna, Long> {

    boolean existsByHash(String hash);

    List<BoletimUrna> findByEleicao_Id(Long eleicaoId);

    List<BoletimUrna> findByUrna_Id(Long urnaId);
}