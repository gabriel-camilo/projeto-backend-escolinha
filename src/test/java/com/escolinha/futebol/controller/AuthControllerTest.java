package com.escolinha.futebol.controller;

import com.escolinha.futebol.dto.LoginRequestDTO;
import com.escolinha.futebol.dto.LoginResponseDTO;
import com.escolinha.futebol.security.JwtUtil;
import com.escolinha.futebol.security.SecurityConfig;
import com.escolinha.futebol.service.AuthService;
import com.escolinha.futebol.service.FuncionarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// Importação nova para Spring Boot 3.4+
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@Import(SecurityConfig.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Substituído @MockBean por @MockitoBean
    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private JwtUtil jwtUtil;

    @MockitoBean
    private FuncionarioService funcionarioService;

    @Test
    void deveRetornar200ETokenAoLogarComSucesso() throws Exception {
        // Cenário (adicionamos @AllArgsConstructor no DTO)
        LoginRequestDTO loginRequest = new LoginRequestDTO("admin@escolinha.com", "123456");
        LoginResponseDTO loginResponse = new LoginResponseDTO("token.jwt.mock", 1L, "Admin");

        // Simulamos que o serviço retorna o DTO com sucesso
        when(authService.login(any(LoginRequestDTO.class))).thenReturn(loginResponse);

        // Ação e Verificação
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("token.jwt.mock"))
                .andExpect(jsonPath("$.nome").value("Admin"));
    }
}