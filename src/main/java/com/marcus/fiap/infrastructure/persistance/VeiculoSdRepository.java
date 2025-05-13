package com.marcus.fiap.infrastructure.persistance;

import com.marcus.fiap.domain.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VeiculoSdRepository extends JpaRepository<Veiculo, Long> {

    List<Veiculo> findByVendidoFalseOrderByPrecoAsc();

    List<Veiculo> findByVendidoTrueOrderByPrecoAsc();

}
