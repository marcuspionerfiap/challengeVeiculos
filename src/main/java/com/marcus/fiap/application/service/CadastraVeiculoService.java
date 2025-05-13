package com.marcus.fiap.application.service;

import com.marcus.fiap.domain.model.Veiculo;
import com.marcus.fiap.infrastructure.persistance.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastraVeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public Veiculo cadastrar(Veiculo veiculo) {
        return veiculoRepository.salvarVeiculo(veiculo);
    }

    public Veiculo editar(Long id, Veiculo veiculoDB) {
        Veiculo veiculo = veiculoRepository.buscarVeiculoPorId(id)
                .orElseThrow(() -> new RuntimeException("Cadastro de veículo não encontrado!"));

        veiculo.setAno(veiculoDB.getAno());
        veiculo.setCor(veiculoDB.getCor());
        veiculo.setMarca(veiculoDB.getMarca());
        veiculo.setModelo(veiculoDB.getModelo());
        veiculo.setPreco(veiculoDB.getPreco());

        return veiculoRepository.salvarVeiculo(veiculo);
    }

    public List<Veiculo> listarVeiculosAVendaPorPreco() {
        return veiculoRepository.listarVeiculosAVendaPorPreco();
    }

    public List<Veiculo> listarVeiculosVendidosPorPreco() {
        return veiculoRepository.listarVeiculosVendidosPorPreco();
    }
}
