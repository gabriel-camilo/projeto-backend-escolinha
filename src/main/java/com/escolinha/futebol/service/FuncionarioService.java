package com.escolinha.futebol.service;

import com.escolinha.futebol.dto.FuncionarioCreateDTO;
import com.escolinha.futebol.dto.FuncionarioResponseDTO;
import com.escolinha.futebol.dto.FuncionarioUpdateDTO;
import com.escolinha.futebol.model.Funcionario;
import com.escolinha.futebol.repository.FuncionarioRepository;
import com.escolinha.futebol.service.exceptions.RegraNegocioException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repo;
    private final PasswordEncoder passwordEncoder;

    public FuncionarioService(FuncionarioRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    // ----------------------------
    // CREATE
    // ----------------------------
    public FuncionarioResponseDTO criar(FuncionarioCreateDTO dto) {
        if (repo.existsByEmail(dto.getEmail())) {
            throw new RegraNegocioException("E-mail já cadastrado!");
        }

        Funcionario f = new Funcionario();
        f.setNome(dto.getNome());
        f.setEmail(dto.getEmail());
        f.setRgCpf(dto.getRgCpf());
        f.setTelefone(dto.getTelefone());
        f.setEndereco(dto.getEndereco());
        f.setDataNascimento(dto.getDataNascimento());
        f.setSenha(passwordEncoder.encode(dto.getSenha()));

        // ⚠️ Definir role padrão
        f.setRole("FUNCIONARIO");

        Funcionario salvo = repo.save(f);
        return toResponseDTO(salvo);
    }

    // ----------------------------
    // READ
    // ----------------------------
    public List<FuncionarioResponseDTO> listarTodos() {
        return repo.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public FuncionarioResponseDTO buscarPorId(Long id) {
        Funcionario f = repo.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado"));
        return toResponseDTO(f);
    }

    public Funcionario findFuncionarioByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado pelo e-mail"));
    }

    // ----------------------------
    // UPDATE
    // ----------------------------
    public FuncionarioResponseDTO atualizar(Long id, FuncionarioUpdateDTO dto) {
        Funcionario f = repo.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado"));

        f.setNome(dto.getNome());
        f.setTelefone(dto.getTelefone());
        f.setEndereco(dto.getEndereco());
        f.setDataNascimento(dto.getDataNascimento());

        Funcionario atualizado = repo.save(f);
        return toResponseDTO(atualizado);
    }

    // ----------------------------
    // DELETE
    // ----------------------------
    public void deletar(Long id) {
        if (!repo.existsById(id)) {
            throw new RegraNegocioException("Funcionário não encontrado para exclusão");
        }
        repo.deleteById(id);
    }

    // ----------------------------
    // CONVERSÃO PARA DTO
    // ----------------------------
    private FuncionarioResponseDTO toResponseDTO(Funcionario f) {
        return new FuncionarioResponseDTO(
                f.getId(),
                f.getNome(),
                f.getEmail(),
                f.getRgCpf(),
                f.getTelefone(),
                f.getEndereco(),
                f.getDataNascimento()
        );
    }
}
