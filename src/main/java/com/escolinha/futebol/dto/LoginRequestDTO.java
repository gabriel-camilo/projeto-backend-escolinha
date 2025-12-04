package com.escolinha.futebol.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Gera Getters, Setters, toString, equals, etc.
@AllArgsConstructor // Gera o construtor com argumentos: new LoginRequestDTO(email, senha) -> Resolve o erro do teste
@NoArgsConstructor  // Gera o construtor vazio -> Necessário para o Spring criar o objeto a partir do JSON
public class LoginRequestDTO {

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "Email inválido.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;
}