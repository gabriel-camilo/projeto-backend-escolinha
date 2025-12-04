package com.escolinha.futebol.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class FuncionarioCreateDTO {

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "Email inválido.")
    private String email;

    @NotBlank(message = "O RG/CPF é obrigatório.")
    private String rgCpf;

    @NotBlank(message = "O telefone é obrigatório.")
    private String telefone;

    @NotBlank(message = "O endereço é obrigatório.")
    private String endereco;

    @NotNull(message = "A data de nascimento é obrigatória.")
    private LocalDate dataNascimento;

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;

    // getters e setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRgCpf() { return rgCpf; }
    public void setRgCpf(String rgCpf) { this.rgCpf = rgCpf; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
