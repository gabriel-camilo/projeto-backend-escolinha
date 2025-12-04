package com.escolinha.futebol.model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;


@Embeddable
@Data
class InscricaoId implements Serializable {
    @Column(name = "aluno_id")
    private Long alunoId;

    @Column(name = "campeonato_id")
    private Long campeonatoId;
}


@Entity
@Data
@Table(name = "inscricoes_campeonato")
public class InscricaoCampeonato {

    @EmbeddedId
    private InscricaoId id = new InscricaoId();

    @ManyToOne
    @MapsId("alunoId")
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @MapsId("campeonatoId")
    @JoinColumn(name = "campeonato_id")
    private Campeonato campeonato;


    private LocalDate dataInscricao = LocalDate.now();
    private Boolean taxaPaga = false;
}