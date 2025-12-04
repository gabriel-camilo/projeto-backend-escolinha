package com.escolinha.futebol.repository;

import com.escolinha.futebol.model.InscricaoCampeonato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InscricaoCampeonatoRepository extends JpaRepository<InscricaoCampeonato, Object> {
}