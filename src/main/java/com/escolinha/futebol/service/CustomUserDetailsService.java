package com.escolinha.futebol.service;

import com.escolinha.futebol.model.Funcionario;
import com.escolinha.futebol.repository.FuncionarioRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final FuncionarioRepository repo;

    public CustomUserDetailsService(FuncionarioRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Funcionario f = repo.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("Usuário não encontrado"));
        return User.withUsername(f.getEmail())
                .password(f.getSenha())
                .roles(f.getRole().replace("ROLE_", "")) // converte ROLE_USER -> USER
                .build();
    }
}
