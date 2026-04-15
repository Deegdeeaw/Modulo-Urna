package com.urna.api.repository;

import com.urna.api.model.BoletimUrna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletimUrnaRepository extends JpaRepository<BoletimUrna, Integer> {
}
