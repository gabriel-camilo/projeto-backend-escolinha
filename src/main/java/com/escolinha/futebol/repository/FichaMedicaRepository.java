package com.escolinha.futebol.repository;

import com.escolinha.futebol.model.FichaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaMedicaRepository extends JpaRepository<FichaMedica, Long> {
}