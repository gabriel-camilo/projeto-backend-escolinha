package com.escolinha.futebol.dto;

import java.time.LocalDate;

public class FuncionarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String rgCpf;
    private String telefone;
    private String endereco;
    private LocalDate dataNascimento;

    public FuncionarioResponseDTO() {}

    public FuncionarioResponseDTO(Long id, String nome, String email, String rgCpf,
                                  String telefone, String endereco,
                                  LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.rgCpf = rgCpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
    }

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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
}
