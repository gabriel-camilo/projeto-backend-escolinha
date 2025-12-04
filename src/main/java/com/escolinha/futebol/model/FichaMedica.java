package com.escolinha.futebol.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "fichas_medicas")
public class FichaMedica {

    // Aqui não usamos @GeneratedValue, pois o ID vem do Aluno
    @Id
    @Column(name = "aluno_id")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String alergias;

    private String tipoSanguineo;

    private String contatoEmergencia;


    @OneToOne
    @MapsId
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
}