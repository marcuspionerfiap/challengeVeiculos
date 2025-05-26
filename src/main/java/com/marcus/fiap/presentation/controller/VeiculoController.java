package com.marcus.fiap.presentation.controller;

import com.marcus.fiap.application.service.VeiculoService;
import com.marcus.fiap.domain.model.Veiculo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @Operation(
            summary = "Cadastrar veículo",
            description = "Realiza o cadastro de um veículo."
    )
    @PostMapping
    public ResponseEntity<Veiculo> cadastrar(@RequestBody @Valid Veiculo veiculo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoService.cadastrar(veiculo));
    }

    @Operation(
            summary = "Editar veículo",
            description = "Realiza a edição de um veículo cadastrado."
    )
    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> editar(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        return ResponseEntity.ok(veiculoService.editar(id, veiculo));
    }

}
