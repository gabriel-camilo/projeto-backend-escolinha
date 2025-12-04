package com.escolinha.futebol.service;

import com.escolinha.futebol.dto.LoginRequestDTO;
import com.escolinha.futebol.dto.LoginResponseDTO;
import com.escolinha.futebol.model.Funcionario;
import com.escolinha.futebol.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final FuncionarioService funcionarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(FuncionarioService funcionarioService,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.funcionarioService = funcionarioService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponseDTO login(LoginRequestDTO request) {

        // Buscar funcionário pelo email
        Funcionario funcionario = funcionarioService.findFuncionarioByEmail(request.getEmail());

        // Validar senha
        if (!passwordEncoder.matches(request.getSenha(), funcionario.getSenha())) {
            throw new RuntimeException("Senha incorreta");
        }

        // Gerar token JWT (somente com email)
        String token = jwtUtil.generateToken(funcionario.getEmail());

        // Retornar DTO de resposta
        return new LoginResponseDTO(
                token,
                funcionario.getId(),
                funcionario.getNome()
        );
    }
}
