package com.urna.api.repository;

import com.urna.api.model.SecaoEleitoral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecaoEleitoralRepository extends JpaRepository<SecaoEleitoral, Long> {

    List<SecaoEleitoral> findByZona_Id(Long zonaId);

}