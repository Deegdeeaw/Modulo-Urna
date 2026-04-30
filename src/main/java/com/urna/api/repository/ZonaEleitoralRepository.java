package com.urna.api.repository;

import com.urna.api.model.ZonaEleitoral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaEleitoralRepository extends JpaRepository<ZonaEleitoral, Long> {

    boolean existsByNumero(Integer numero);

}