package com.urna.api.repository;

import com.urna.api.model.Apuracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApuracaoRepository extends JpaRepository<Apuracao, Long> {

    boolean existsByCandidato_IdAndEleicao_Id(Long candidatoId, Long eleicaoId);

    Apuracao findByCandidato_IdAndEleicao_Id(Long candidatoId, Long eleicaoId);

    List<Apuracao> findByEleicao_IdOrderByTotalVotosDesc(Long eleicaoId);
}