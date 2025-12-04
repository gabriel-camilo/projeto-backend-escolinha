package com.escolinha.futebol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.escolinha.futebol.model.Funcionario;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByEmail(String email);
    boolean existsByEmail(String email);
}
