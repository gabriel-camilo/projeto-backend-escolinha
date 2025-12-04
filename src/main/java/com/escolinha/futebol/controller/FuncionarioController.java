package com.escolinha.futebol.controller;

import com.escolinha.futebol.dto.FuncionarioCreateDTO;
import com.escolinha.futebol.dto.FuncionarioResponseDTO;
import com.escolinha.futebol.dto.FuncionarioUpdateDTO;
import com.escolinha.futebol.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
@CrossOrigin(origins = "http://localhost:4200")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> criar(
            @RequestBody @Valid FuncionarioCreateDTO dto
    ) {
        FuncionarioResponseDTO criado = funcionarioService.criar(dto);
        return ResponseEntity.ok(criado);
    }

    // LISTAR TODOS
    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(funcionarioService.listarTodos());
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> buscarPorId(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(funcionarioService.buscarPorId(id));
    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid FuncionarioUpdateDTO dto
    ) {
        return ResponseEntity.ok(funcionarioService.atualizar(id, dto));
    }

    // DELETAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        funcionarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
