package com.escolinha.futebol.config;

import com.escolinha.futebol.model.Funcionario;
import com.escolinha.futebol.repository.FuncionarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

@Configuration
public class DataInitializer implements CommandLineRunner {

    private final FuncionarioRepository funcionarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(FuncionarioRepository funcionarioRepository, PasswordEncoder passwordEncoder) {
        this.funcionarioRepository = funcionarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Verifica se já existe o usuário admin para não criar duplicado a cada restart
        Optional<Funcionario> adminExistente = funcionarioRepository.findByEmail("admin@escolinha.com");

        if (adminExistente.isEmpty()) {
            Funcionario admin = new Funcionario();

            // Campos Obrigatórios (Conforme sua entidade Funcionario)
            admin.setNome("Administrador do Sistema");
            admin.setEmail("admin@escolinha.com");
            admin.setSenha(passwordEncoder.encode("123456"));
            admin.setRole("ROLE_ADMIN"); // Este campo estava faltando e é obrigatório!

            // Campos Opcionais (Preenchendo para ficar completo)
            admin.setRgCpf("000.000.000-00");
            admin.setTelefone("(11) 99999-9999");
            admin.setEndereco("Rua da Escolinha, 100");
            admin.setDataNascimento(LocalDate.of(1990, 1, 1));

            funcionarioRepository.save(admin);

            System.out.println("---------------------------------");
            System.out.println(" USUÁRIO ADMIN CRIADO COM SUCESSO");
            System.out.println(" Email: admin@escolinha.com");
            System.out.println(" Senha: 123456");
            System.out.println("---------------------------------");
        } else {
            System.out.println("---------------------------------");
            System.out.println("ℹ️ Usuário Admin já existe no banco.");
            System.out.println("---------------------------------");
        }
    }
}