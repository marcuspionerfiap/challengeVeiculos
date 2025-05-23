package com.marcus.fiap;

import com.marcus.fiap.application.service.VeiculoService;
import com.marcus.fiap.domain.model.Veiculo;
import com.marcus.fiap.infrastructure.persistance.repository.VeiculoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VeiculoServiceTest {

    @InjectMocks
    private VeiculoService veiculoService;

    @Mock
    private VeiculoRepository veiculoRepository;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarVeiculo() {
        Veiculo veiculo = new Veiculo();
        when(veiculoRepository.salvarVeiculo(veiculo)).thenReturn(veiculo);

        Veiculo resultado = veiculoService.cadastrar(veiculo);

        assertNotNull(resultado);
        verify(veiculoRepository).salvarVeiculo(veiculo);
    }

    @Test
    void deveEditarVeiculoExistente() {
        Long id = 1L;
        Veiculo veiculoExistente = new Veiculo();
        veiculoExistente.setId(id);

        Veiculo novoVeiculo = new Veiculo();
        novoVeiculo.setAno(2020);
        novoVeiculo.setCor("Azul");
        novoVeiculo.setMarca("Fiat");
        novoVeiculo.setModelo("Uno");
        novoVeiculo.setPreco(BigDecimal.valueOf(20000.0));

        when(veiculoRepository.buscarVeiculoPorId(id)).thenReturn(Optional.of(veiculoExistente));
        when(veiculoRepository.salvarVeiculo(veiculoExistente)).thenReturn(veiculoExistente);

        Veiculo resultado = veiculoService.editar(id, novoVeiculo);

        assertEquals("Fiat", resultado.getMarca());
        assertEquals("Uno", resultado.getModelo());
        assertEquals("Azul", resultado.getCor());
        assertEquals(2020, resultado.getAno());
        assertEquals(BigDecimal.valueOf(20000.0), resultado.getPreco());
    }

    @Test
    void deveLancarExcecaoAoEditarVeiculoInexistente() {
        Long id = 1L;
        Veiculo veiculo = new Veiculo();

        when(veiculoRepository.buscarVeiculoPorId(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                veiculoService.editar(id, veiculo)
        );

        assertEquals("Cadastro de veículo não encontrado!", exception.getMessage());
    }

    @Test
    void deveListarVeiculosAVendaPorPreco() {
        List<Veiculo> listaMock = List.of(new Veiculo(), new Veiculo());
        when(veiculoRepository.listarVeiculosAVendaPorPreco()).thenReturn(listaMock);

        List<Veiculo> resultado = veiculoService.listarVeiculosAVendaPorPreco();

        assertEquals(2, resultado.size());
        verify(veiculoRepository).listarVeiculosAVendaPorPreco();
    }

    @Test
    void deveListarVeiculosVendidosPorPreco() {
        List<Veiculo> listaMock = List.of(new Veiculo());
        when(veiculoRepository.listarVeiculosVendidosPorPreco()).thenReturn(listaMock);

        List<Veiculo> resultado = veiculoService.listarVeiculosVendidosPorPreco();

        assertEquals(1, resultado.size());
        verify(veiculoRepository).listarVeiculosVendidosPorPreco();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
}
