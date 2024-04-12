package Prueba.apibanco.service.impl;

import Prueba.apibanco.dto.CuentaDTO;
import Prueba.apibanco.exception.RecursoNoEncontradoException;
import Prueba.apibanco.model.Cliente;
import Prueba.apibanco.model.Cuenta;
import Prueba.apibanco.repository.CuentaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CuentaServiceTest {

    @Mock
    private ModelMapper mockModelMapper;
    @Mock
    private CuentaRepository mockCuentaRepository;

    @InjectMocks
    private CuentaService cuentaServiceUnderTest;

    @Test
    void testListarCuenta() {
        final Cuenta cuenta = new Cuenta();
        cuenta.setId(0);
        cuenta.setNumeroCuenta(0);
        cuenta.setSaldo(0.0);
        final Cliente cliente = new Cliente();
        cliente.setIdCliente(0);
        cuenta.setCliente(cliente);
        final List<Cuenta> cuentas = List.of(cuenta);
        when(mockCuentaRepository.findAll()).thenReturn(cuentas);

        final CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setId(0);
        cuentaDTO.setNumeroCuenta(0);
        cuentaDTO.setSaldo(0.0);
        final Cliente cliente1 = new Cliente();
        cliente1.setIdCliente(0);
        cuentaDTO.setCliente(cliente1);
        final Cuenta source = new Cuenta();
        source.setId(0);
        source.setNumeroCuenta(0);
        source.setSaldo(0.0);
        final Cliente cliente2 = new Cliente();
        cliente2.setIdCliente(0);
        source.setCliente(cliente2);
        when(mockModelMapper.map(source, CuentaDTO.class)).thenReturn(cuentaDTO);


        final List<CuentaDTO> result = cuentaServiceUnderTest.listarCuenta();

    }

    @Test
    void testListarCuenta_CuentaRepositoryReturnsNoItems() {

        when(mockCuentaRepository.findAll()).thenReturn(Collections.emptyList());
        final List<CuentaDTO> result = cuentaServiceUnderTest.listarCuenta();
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testBuscarCuentaPorId() {

        final Cuenta cuenta1 = new Cuenta();
        cuenta1.setId(0);
        cuenta1.setNumeroCuenta(0);
        cuenta1.setSaldo(0.0);
        final Cliente cliente = new Cliente();
        cliente.setIdCliente(0);
        cuenta1.setCliente(cliente);
        final Optional<Cuenta> cuenta = Optional.of(cuenta1);
        when(mockCuentaRepository.findById(0)).thenReturn(cuenta);

        final CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setId(0);
        cuentaDTO.setNumeroCuenta(0);
        cuentaDTO.setSaldo(0.0);
        final Cliente cliente1 = new Cliente();
        cliente1.setIdCliente(0);
        cuentaDTO.setCliente(cliente1);
        final Cuenta source = new Cuenta();
        source.setId(0);
        source.setNumeroCuenta(0);
        source.setSaldo(0.0);
        final Cliente cliente2 = new Cliente();
        cliente2.setIdCliente(0);
        source.setCliente(cliente2);
        when(mockModelMapper.map(source, CuentaDTO.class)).thenReturn(cuentaDTO);

        final CuentaDTO result = cuentaServiceUnderTest.buscarCuentaPorId(0);

    }

    @Test
    void testBuscarCuentaPorId_CuentaRepositoryReturnsAbsent() {
        when(mockCuentaRepository.findById(0)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> cuentaServiceUnderTest.buscarCuentaPorId(0))
                .isInstanceOf(RecursoNoEncontradoException.class);
    }

    @Test
    void testBuscarCuentaPorId_ModelMapperReturnsNull() {

        final Cuenta cuenta1 = new Cuenta();
        cuenta1.setId(0);
        cuenta1.setNumeroCuenta(0);
        cuenta1.setSaldo(0.0);
        final Cliente cliente = new Cliente();
        cliente.setIdCliente(0);
        cuenta1.setCliente(cliente);
        final Optional<Cuenta> cuenta = Optional.of(cuenta1);
        when(mockCuentaRepository.findById(0)).thenReturn(cuenta);

        final Cuenta source = new Cuenta();
        source.setId(0);
        source.setNumeroCuenta(0);
        source.setSaldo(0.0);
        final Cliente cliente1 = new Cliente();
        cliente1.setIdCliente(0);
        source.setCliente(cliente1);
        when(mockModelMapper.map(source, CuentaDTO.class)).thenReturn(null);

        final CuentaDTO result = cuentaServiceUnderTest.buscarCuentaPorId(0);
        assertThat(result).isNull();
    }

    @Test
    void testActualizarCuenta() {

        final CuentaDTO cuentaDT = new CuentaDTO();
        cuentaDT.setId(0);
        cuentaDT.setNumeroCuenta(0);
        cuentaDT.setSaldo(0.0);
        final Cliente cliente = new Cliente();
        cliente.setIdCliente(0);
        cuentaDT.setCliente(cliente);

        final Cuenta cuenta1 = new Cuenta();
        cuenta1.setId(0);
        cuenta1.setNumeroCuenta(0);
        cuenta1.setSaldo(0.0);
        final Cliente cliente1 = new Cliente();
        cliente1.setIdCliente(0);
        cuenta1.setCliente(cliente1);
        final Optional<Cuenta> cuenta = Optional.of(cuenta1);
        when(mockCuentaRepository.findById(0)).thenReturn(cuenta);

        final Cuenta cuenta2 = new Cuenta();
        cuenta2.setId(0);
        cuenta2.setNumeroCuenta(0);
        cuenta2.setSaldo(0.0);
        final Cliente cliente2 = new Cliente();
        cliente2.setIdCliente(0);
        cuenta2.setCliente(cliente2);
        final Cuenta entity = new Cuenta();
        entity.setId(0);
        entity.setNumeroCuenta(0);
        entity.setSaldo(0.0);
        final Cliente cliente3 = new Cliente();
        cliente3.setIdCliente(0);
        entity.setCliente(cliente3);
        when(mockCuentaRepository.save(entity)).thenReturn(cuenta2);

        final CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setId(0);
        cuentaDTO.setNumeroCuenta(0);
        cuentaDTO.setSaldo(0.0);
        final Cliente cliente4 = new Cliente();
        cliente4.setIdCliente(0);
        cuentaDTO.setCliente(cliente4);
        final Cuenta source = new Cuenta();
        source.setId(0);
        source.setNumeroCuenta(0);
        source.setSaldo(0.0);
        final Cliente cliente5 = new Cliente();
        cliente5.setIdCliente(0);
        source.setCliente(cliente5);
        when(mockModelMapper.map(source, CuentaDTO.class)).thenReturn(cuentaDTO);

        final CuentaDTO result = cuentaServiceUnderTest.actualizarCuenta(cuentaDT);

    }

    @Test
    void testActualizarCuenta_CuentaRepositoryFindByIdReturnsAbsent() {
        final CuentaDTO cuentaDT = new CuentaDTO();
        cuentaDT.setId(0);
        cuentaDT.setNumeroCuenta(0);
        cuentaDT.setSaldo(0.0);
        final Cliente cliente = new Cliente();
        cliente.setIdCliente(0);
        cuentaDT.setCliente(cliente);

        when(mockCuentaRepository.findById(0)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> cuentaServiceUnderTest.actualizarCuenta(cuentaDT))
                .isInstanceOf(RecursoNoEncontradoException.class);
    }

    @Test
    void testGuardarCuenta() {
        final CuentaDTO cuenta = new CuentaDTO();
        cuenta.setId(0);
        cuenta.setNumeroCuenta(0);
        cuenta.setSaldo(0.0);
        final Cliente cliente = new Cliente();
        cliente.setIdCliente(0);
        cuenta.setCliente(cliente);

        final Cuenta cuenta1 = new Cuenta();
        cuenta1.setId(0);
        cuenta1.setNumeroCuenta(0);
        cuenta1.setSaldo(0.0);
        final Cliente cliente1 = new Cliente();
        cliente1.setIdCliente(0);
        cuenta1.setCliente(cliente1);
        when(mockModelMapper.map(any(Object.class), eq(Cuenta.class))).thenReturn(cuenta1);

        final Cuenta cuenta2 = new Cuenta();
        cuenta2.setId(0);
        cuenta2.setNumeroCuenta(0);
        cuenta2.setSaldo(0.0);
        final Cliente cliente2 = new Cliente();
        cliente2.setIdCliente(0);
        cuenta2.setCliente(cliente2);
        final Cuenta entity = new Cuenta();
        entity.setId(0);
        entity.setNumeroCuenta(0);
        entity.setSaldo(0.0);
        final Cliente cliente3 = new Cliente();
        cliente3.setIdCliente(0);
        entity.setCliente(cliente3);
        when(mockCuentaRepository.save(entity)).thenReturn(cuenta2);

        final CuentaDTO result = cuentaServiceUnderTest.guardarCuenta(cuenta);

    }

    @Test
    void testEliminarCuentaPorId() {
        // Setup
        // Configure CuentaRepository.findById(...).
        final Cuenta cuenta1 = new Cuenta();
        cuenta1.setId(0);
        cuenta1.setNumeroCuenta(0);
        cuenta1.setSaldo(0.0);
        final Cliente cliente = new Cliente();
        cliente.setIdCliente(0);
        cuenta1.setCliente(cliente);
        final Optional<Cuenta> cuenta = Optional.of(cuenta1);
        when(mockCuentaRepository.findById(0)).thenReturn(cuenta);

        // Configure ModelMapper.map(...).
        final CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setId(0);
        cuentaDTO.setNumeroCuenta(0);
        cuentaDTO.setSaldo(0.0);
        final Cliente cliente1 = new Cliente();
        cliente1.setIdCliente(0);
        cuentaDTO.setCliente(cliente1);
        final Cuenta source = new Cuenta();
        source.setId(0);
        source.setNumeroCuenta(0);
        source.setSaldo(0.0);
        final Cliente cliente2 = new Cliente();
        cliente2.setIdCliente(0);
        source.setCliente(cliente2);
        when(mockModelMapper.map(source, CuentaDTO.class)).thenReturn(cuentaDTO);

        cuentaServiceUnderTest.eliminarCuentaPorId(0);
        verify(mockCuentaRepository).deleteById(0);
    }

    @Test
    void testEliminarCuentaPorId_CuentaRepositoryFindByIdReturnsAbsent() {
        when(mockCuentaRepository.findById(0)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> cuentaServiceUnderTest.eliminarCuentaPorId(0))
                .isInstanceOf(RecursoNoEncontradoException.class);
    }

    @Test
    void testEliminarCuentaPorId_ModelMapperReturnsNull() {

        final Cuenta cuenta1 = new Cuenta();
        cuenta1.setId(0);
        cuenta1.setNumeroCuenta(0);
        cuenta1.setSaldo(0.0);
        final Cliente cliente = new Cliente();
        cliente.setIdCliente(0);
        cuenta1.setCliente(cliente);
        final Optional<Cuenta> cuenta = Optional.of(cuenta1);
        when(mockCuentaRepository.findById(0)).thenReturn(cuenta);

        final Cuenta source = new Cuenta();
        source.setId(0);
        source.setNumeroCuenta(0);
        source.setSaldo(0.0);
        final Cliente cliente1 = new Cliente();
        cliente1.setIdCliente(0);
        source.setCliente(cliente1);
        when(mockModelMapper.map(source, CuentaDTO.class)).thenReturn(null);

        assertThatThrownBy(() -> cuentaServiceUnderTest.eliminarCuentaPorId(0))
                .isInstanceOf(RecursoNoEncontradoException.class);
    }
}
