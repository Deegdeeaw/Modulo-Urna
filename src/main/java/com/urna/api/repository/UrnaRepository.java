package com.urna.api.repository;

import com.urna.api.model.Urna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrnaRepository extends JpaRepository<Urna, Long> {
}