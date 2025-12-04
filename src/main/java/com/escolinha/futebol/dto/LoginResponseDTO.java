package com.escolinha.futebol.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Gera Getters, Setters, toString, etc.
@AllArgsConstructor // Substitui seu construtor manual (token, id, nome)
@NoArgsConstructor  // Adiciona um construtor vazio (boa prática para serialização)
public class LoginResponseDTO {

    private String token;
    private Long id;
    private String nome;
}