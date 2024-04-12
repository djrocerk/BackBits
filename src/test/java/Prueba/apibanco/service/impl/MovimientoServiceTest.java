package Prueba.apibanco.service.impl;

import Prueba.apibanco.dto.MovimientoDTO;
import Prueba.apibanco.exception.RecursoNoEncontradoException;
import Prueba.apibanco.model.Cliente;
import Prueba.apibanco.model.Cuenta;
import Prueba.apibanco.model.Movimiento;
import Prueba.apibanco.model.TipoMovimiento;
import Prueba.apibanco.repository.CuentaRepository;
import Prueba.apibanco.repository.MovimientoRepository;
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
class MovimientoServiceTest {

    @Mock
    private ModelMapper mockModelMapper;
    @Mock
    private MovimientoRepository mockMovimientoRepository;
    @Mock
    private CuentaRepository mockCuentaRepository;

    @InjectMocks
    private MovimientoService movimientoServiceUnderTest;

    @Test
    void testListarMovimiento() {

        final Movimiento movimiento = new Movimiento();
        movimiento.setValor(0.0);
        final Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(0);
        cuenta.setSaldo(0.0);
        movimiento.setCuenta(cuenta);
        final TipoMovimiento tipoMovimiento = new TipoMovimiento();
        tipoMovimiento.setIdTipoMovimiento(0);
        movimiento.setTipoMovimiento(tipoMovimiento);
        final List<Movimiento> movimientos = List.of(movimiento);
        when(mockMovimientoRepository.findAll()).thenReturn(movimientos);


        final MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setValor(0.0);
        final Cuenta cuenta1 = new Cuenta();
        cuenta1.setNumeroCuenta(0);
        cuenta1.setSaldo(0.0);
        movimientoDTO.setCuenta(cuenta1);
        final TipoMovimiento tipoMovimiento1 = new TipoMovimiento();
        tipoMovimiento1.setIdTipoMovimiento(0);
        movimientoDTO.setTipoMovimiento(tipoMovimiento1);
        final Movimiento source = new Movimiento();
        source.setValor(0.0);
        final Cuenta cuenta2 = new Cuenta();
        cuenta2.setNumeroCuenta(0);
        cuenta2.setSaldo(0.0);
        source.setCuenta(cuenta2);
        final TipoMovimiento tipoMovimiento2 = new TipoMovimiento();
        tipoMovimiento2.setIdTipoMovimiento(0);
        source.setTipoMovimiento(tipoMovimiento2);
        when(mockModelMapper.map(source, MovimientoDTO.class)).thenReturn(movimientoDTO);

        final List<MovimientoDTO> result = movimientoServiceUnderTest.listarMovimiento();

    }

    @Test
    void testListarMovimiento_MovimientoRepositoryReturnsNoItems() {
        when(mockMovimientoRepository.findAll()).thenReturn(Collections.emptyList());

        final List<MovimientoDTO> result = movimientoServiceUnderTest.listarMovimiento();
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testBuscarMovimientoPorId() {

        final Movimiento movimiento1 = new Movimiento();
        movimiento1.setValor(0.0);
        final Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(0);
        cuenta.setSaldo(0.0);
        movimiento1.setCuenta(cuenta);
        final TipoMovimiento tipoMovimiento = new TipoMovimiento();
        tipoMovimiento.setIdTipoMovimiento(0);
        movimiento1.setTipoMovimiento(tipoMovimiento);
        final Optional<Movimiento> movimiento = Optional.of(movimiento1);
        when(mockMovimientoRepository.findById(0)).thenReturn(movimiento);


        final MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setValor(0.0);
        final Cuenta cuenta1 = new Cuenta();
        cuenta1.setNumeroCuenta(0);
        cuenta1.setSaldo(0.0);
        movimientoDTO.setCuenta(cuenta1);
        final TipoMovimiento tipoMovimiento1 = new TipoMovimiento();
        tipoMovimiento1.setIdTipoMovimiento(0);
        movimientoDTO.setTipoMovimiento(tipoMovimiento1);
        final Movimiento source = new Movimiento();
        source.setValor(0.0);
        final Cuenta cuenta2 = new Cuenta();
        cuenta2.setNumeroCuenta(0);
        cuenta2.setSaldo(0.0);
        source.setCuenta(cuenta2);
        final TipoMovimiento tipoMovimiento2 = new TipoMovimiento();
        tipoMovimiento2.setIdTipoMovimiento(0);
        source.setTipoMovimiento(tipoMovimiento2);
        when(mockModelMapper.map(source, MovimientoDTO.class)).thenReturn(movimientoDTO);

        final MovimientoDTO result = movimientoServiceUnderTest.buscarMovimientoPorId(0);

    }

    @Test
    void testBuscarMovimientoPorId_MovimientoRepositoryReturnsAbsent() {
        when(mockMovimientoRepository.findById(0)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> movimientoServiceUnderTest.buscarMovimientoPorId(0))
                .isInstanceOf(RecursoNoEncontradoException.class);
    }

    @Test
    void testCrearMovimiento() {

        final MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setValor(0.0);

        final Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(0);
        cuenta.setSaldo(0.0);
        movimientoDTO.setCuenta(cuenta);

        final TipoMovimiento tipoMovimiento = new TipoMovimiento();
        tipoMovimiento.setIdTipoMovimiento(0);
        movimientoDTO.setTipoMovimiento(tipoMovimiento);

        final Cuenta cuentaMock = new Cuenta();
        cuentaMock.setId(0);
        cuentaMock.setNumeroCuenta(0);
        cuentaMock.setSaldo(0.0);

        final Cliente cliente = new Cliente();
        cliente.setIdCliente(0);
        cuentaMock.setCliente(cliente);

        when(mockCuentaRepository.findByNumeroCuenta(0)).thenReturn(cuentaMock);

        final Movimiento movimientoMock = new Movimiento();
        movimientoMock.setValor(0.0);
        movimientoMock.setCuenta(cuenta);
        movimientoMock.setTipoMovimiento(tipoMovimiento);

        when(mockModelMapper.map(any(), eq(Movimiento.class))).thenReturn(movimientoMock);

        final Movimiento movimientoGuardadoMock = new Movimiento();
        movimientoGuardadoMock.setValor(0.0);
        movimientoGuardadoMock.setCuenta(cuenta);
        movimientoGuardadoMock.setTipoMovimiento(tipoMovimiento);

        when(mockMovimientoRepository.save(any(Movimiento.class))).thenReturn(movimientoGuardadoMock);

        final MovimientoDTO resultadoFinal = movimientoServiceUnderTest.crearMovimiento(movimientoDTO);

        final Cuenta entidadCuentaVerificar = new Cuenta();
        entidadCuentaVerificar.setId(0);
        entidadCuentaVerificar.setNumeroCuenta(0);
        entidadCuentaVerificar.setSaldo(0.0);
        final Cliente clienteVerificar = new Cliente();
        clienteVerificar.setIdCliente(0);
        entidadCuentaVerificar.setCliente(clienteVerificar);
        verify(mockCuentaRepository).save(entidadCuentaVerificar);
    }


    @Test
    void testCrearMovimiento_CuentaRepositoryFindByNumeroCuentaReturnsNull() {

        final MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setValor(0.0);
        final Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(0);
        cuenta.setSaldo(0.0);
        movimientoDTO.setCuenta(cuenta);
        final TipoMovimiento tipoMovimiento = new TipoMovimiento();
        tipoMovimiento.setIdTipoMovimiento(0);
        movimientoDTO.setTipoMovimiento(tipoMovimiento);

        when(mockCuentaRepository.findByNumeroCuenta(0)).thenReturn(null);
        assertThatThrownBy(() -> movimientoServiceUnderTest.crearMovimiento(movimientoDTO))
                .isInstanceOf(RecursoNoEncontradoException.class);
    }

    @Test
    void testEliminarMovimientoPorId() {

        final Movimiento movimiento1 = new Movimiento();
        movimiento1.setValor(0.0);
        final Cuenta cuenta = new Cuenta();
        cuenta.setId(0);
        cuenta.setNumeroCuenta(0);
        cuenta.setSaldo(0.0);
        final Cliente cliente = new Cliente();
        cliente.setIdCliente(0);

        cliente.setNombre("Nombre Cliente");
        cliente.setDireccion("Dirección Cliente");
        cliente.setTelefono("Teléfono Cliente");
        cuenta.setCliente(cliente);
        movimiento1.setCuenta(cuenta);
        final TipoMovimiento tipoMovimiento = new TipoMovimiento();
        tipoMovimiento.setIdTipoMovimiento(0);
        movimiento1.setTipoMovimiento(tipoMovimiento);
        final Optional<Movimiento> movimiento = Optional.of(movimiento1);
        when(mockMovimientoRepository.findById(0)).thenReturn(movimiento);

        movimientoServiceUnderTest.eliminarMovimientoPorId(0);

        final Cuenta entity = new Cuenta();
        entity.setId(0);
        entity.setNumeroCuenta(0);
        entity.setSaldo(0.0);
        final Cliente clienteEntity = new Cliente();
        clienteEntity.setIdCliente(0);


        clienteEntity.setNombre("Nombre Cliente");
        clienteEntity.setDireccion("Dirección Cliente");
        clienteEntity.setTelefono("Teléfono Cliente");
        entity.setCliente(clienteEntity);
        verify(mockCuentaRepository).save(entity);
        verify(mockMovimientoRepository).deleteById(0);
    }


    @Test
    void testEliminarMovimientoPorId_MovimientoRepositoryFindByIdReturnsAbsent() {
        // Setup
        when(mockMovimientoRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> movimientoServiceUnderTest.eliminarMovimientoPorId(0))
                .isInstanceOf(RecursoNoEncontradoException.class);
    }
}
