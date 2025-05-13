package com.marcus.fiap.infrastructure.persistance.repository;

import com.marcus.fiap.domain.model.Veiculo;

import java.util.List;
import java.util.Optional;

public interface VeiculoRepository {

    Veiculo salvarVeiculo(Veiculo veiculo);

    Optional<Veiculo> buscarVeiculoPorId(Long id);

    List<Veiculo> listarVeiculosAVendaPorPreco();

    List<Veiculo> listarVeiculosVendidosPorPreco();

}
