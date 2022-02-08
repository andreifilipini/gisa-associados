package com.gisa.gisaassociados.repository;

import com.gisa.gisaassociados.model.Associado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {

    Optional<Associado> findByUserId(String userId);
}
