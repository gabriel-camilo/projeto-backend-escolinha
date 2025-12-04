package com.escolinha.futebol.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "campeonatos")
public class Campeonato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private LocalDate dataInicio;

    @OneToMany(mappedBy = "campeonato")
    private List<InscricaoCampeonato> inscricoes;
}