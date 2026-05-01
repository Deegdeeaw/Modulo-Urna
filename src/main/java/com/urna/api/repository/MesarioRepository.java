package com.urna.api.repository;

import com.urna.api.model.Mesario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesarioRepository extends JpaRepository<Mesario, Long> {

    boolean existsByCpf(String cpf);

}