package com.urna.api.repository;

import com.urna.api.model.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UfRepository extends JpaRepository<Uf, Long> {

    boolean existsBySigla(String sigla);

    Uf findBySigla(String sigla);

}