package com.escolinha.futebol.service;

import com.escolinha.futebol.dto.LoginRequestDTO;
import com.escolinha.futebol.dto.LoginResponseDTO;
import com.escolinha.futebol.model.Funcionario;
import com.escolinha.futebol.security.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private FuncionarioService funcionarioService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @Test
    void deveRealizarLoginComSucesso() {
        // Cenario
        String email = "teste@escolinha.com";
        String senha = "123";
        String tokenGerado = "token.jwt.falso";

        LoginRequestDTO request = new LoginRequestDTO();
        request.setEmail(email);
        request.setSenha(senha);

        Funcionario funcionarioMock = new Funcionario();
        funcionarioMock.setId(1L);
        funcionarioMock.setEmail(email);
        funcionarioMock.setSenha("senhaCodificada");
        funcionarioMock.setNome("João Treinador");

        // Mockando comportamentos
        when(funcionarioService.findFuncionarioByEmail(email)).thenReturn(funcionarioMock);
        when(passwordEncoder.matches(senha, "senhaCodificada")).thenReturn(true);
        when(jwtUtil.generateToken(email)).thenReturn(tokenGerado);

        // Ação
        LoginResponseDTO response = authService.login(request);

        // Verificação
        Assertions.assertNotNull(response);
        Assertions.assertEquals(tokenGerado, response.getToken());
        Assertions.assertEquals("João Treinador", response.getNome());

        verify(jwtUtil, Mockito.times(1)).generateToken(email);
    }

    @Test
    void deveLancarErroQuandoSenhaIncorreta() {
        // Cenario
        String email = "teste@escolinha.com";
        String senhaErrada = "000";

        LoginRequestDTO request = new LoginRequestDTO();
        request.setEmail(email);
        request.setSenha(senhaErrada);

        Funcionario funcionarioMock = new Funcionario();
        funcionarioMock.setEmail(email);
        funcionarioMock.setSenha("senhaCorretaCodificada");

        // Mockando comportamentos
        when(funcionarioService.findFuncionarioByEmail(email)).thenReturn(funcionarioMock);
        when(passwordEncoder.matches(senhaErrada, "senhaCorretaCodificada")).thenReturn(false);

        // Ação e Verificação
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            authService.login(request);
        });

        Assertions.assertEquals("Senha incorreta", exception.getMessage());
    }
}