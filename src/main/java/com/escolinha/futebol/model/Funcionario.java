package com.escolinha.futebol.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    private String rgCpf;

    private String telefone;

    private String endereco;

    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String senha; // hashed

    @Column(nullable = false)
    private String role; // ex: ROLE_ADMIN, ROLE_USER

    // -------------------------
    // CONSTRUTORES
    // -------------------------

    public Funcionario() {}

    public Funcionario(
            String nome,
            String email,
            String rgCpf,
            String telefone,
            String endereco,
            LocalDate dataNascimento,
            String senha,
            String role
    ) {
        this.nome = nome;
        this.email = email;
        this.rgCpf = rgCpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
        this.role = role;
    }

    // -------------------------
    // GETTERS E SETTERS
    // -------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRgCpf() {
        return rgCpf;
    }

    public void setRgCpf(String rgCpf) {
        this.rgCpf = rgCpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
