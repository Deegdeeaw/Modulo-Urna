package com.urna.api.repository;

import com.urna.api.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
