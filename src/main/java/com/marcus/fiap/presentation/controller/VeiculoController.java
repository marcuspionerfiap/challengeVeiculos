package com.marcus.fiap.presentation.controller;

import com.marcus.fiap.application.service.VeiculoService;
import com.marcus.fiap.domain.model.Veiculo;
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

    @PostMapping
    public ResponseEntity<Veiculo> cadastrar(@RequestBody @Valid Veiculo veiculo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoService.cadastrar(veiculo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> editar(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        return ResponseEntity.ok(veiculoService.editar(id, veiculo));
    }

    @GetMapping("/listaVeiculosAVenda")
    public List<Veiculo> listaVeiculosAVenda() {
        return veiculoService.listarVeiculosAVendaPorPreco();
    }

    @GetMapping("/listaVeiculosVendidos")
    public List<Veiculo> listaVeiculosVendidos() {
        return veiculoService.listarVeiculosVendidosPorPreco();
    }
}
