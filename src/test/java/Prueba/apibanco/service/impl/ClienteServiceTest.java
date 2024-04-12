package Prueba.apibanco.service.impl;

import Prueba.apibanco.dto.ClienteDTO;
import Prueba.apibanco.exception.RecursoNoEncontradoException;
import Prueba.apibanco.model.Cliente;
import Prueba.apibanco.repository.ClienteRepository;
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
class ClienteServiceTest {

    @Mock
    private ModelMapper mockModelMapper;
    @Mock
    private ClienteRepository mockClienteRepository;

    @InjectMocks
    private ClienteService clienteServiceUnderTest;

    @Test
    void testListarCliente() {

        final List<Cliente> clientes = List.of(new Cliente(0, "nombre", "direccion", "telefono"));
        when(mockClienteRepository.findAll()).thenReturn(clientes);
        final ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdCliente(0);
        clienteDTO.setNombre("nombre");
        clienteDTO.setDireccion("direccion");
        clienteDTO.setTelefono("telefono");
        when(mockModelMapper.map(new Cliente(0, "nombre", "direccion", "telefono"), ClienteDTO.class))
                .thenReturn(clienteDTO);
        final List<ClienteDTO> result = clienteServiceUnderTest.listarCliente();
    }

    @Test
    void testListarCliente_ClienteRepositoryReturnsNoItems() {

        when(mockClienteRepository.findAll()).thenReturn(Collections.emptyList());
        final List<ClienteDTO> result = clienteServiceUnderTest.listarCliente();
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testBuscarClientePorId() {

        final Optional<Cliente> cliente = Optional.of(new Cliente(0, "nombre", "direccion", "telefono"));
        when(mockClienteRepository.findById(0)).thenReturn(cliente);

        // Configure ModelMapper.map(...).
        final ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdCliente(0);
        clienteDTO.setNombre("nombre");
        clienteDTO.setDireccion("direccion");
        clienteDTO.setTelefono("telefono");
        when(mockModelMapper.map(new Cliente(0, "nombre", "direccion", "telefono"), ClienteDTO.class))
                .thenReturn(clienteDTO);

        final ClienteDTO result = clienteServiceUnderTest.buscarClientePorId(0);
    }

    @Test
    void testBuscarClientePorId_ClienteRepositoryReturnsAbsent() {

        when(mockClienteRepository.findById(0)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> clienteServiceUnderTest.buscarClientePorId(0))
                .isInstanceOf(RecursoNoEncontradoException.class);
    }

    @Test
    void testActualizarCliente() {

        final ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdCliente(0);
        clienteDTO.setNombre("nombre");
        clienteDTO.setDireccion("direccion");
        clienteDTO.setTelefono("telefono");

        final Optional<Cliente> cliente = Optional.of(new Cliente(0, "nombre", "direccion", "telefono"));
        when(mockClienteRepository.findById(0)).thenReturn(cliente);


        final Cliente cliente1 = new Cliente(0, "nombre", "direccion", "telefono");
        when(mockClienteRepository.save(new Cliente(0, "nombre", "direccion", "telefono"))).thenReturn(cliente1);

        // Configure ModelMapper.map(...).
        final ClienteDTO clienteDTO1 = new ClienteDTO();
        clienteDTO1.setIdCliente(0);
        clienteDTO1.setNombre("nombre");
        clienteDTO1.setDireccion("direccion");
        clienteDTO1.setTelefono("telefono");
        when(mockModelMapper.map(new Cliente(0, "nombre", "direccion", "telefono"), ClienteDTO.class))
                .thenReturn(clienteDTO1);

        final ClienteDTO result = clienteServiceUnderTest.actualizarCliente(clienteDTO);

    }

    @Test
    void testActualizarCliente_ClienteRepositoryFindByIdReturnsAbsent() {
        // Setup
        final ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdCliente(0);
        clienteDTO.setNombre("nombre");
        clienteDTO.setDireccion("direccion");
        clienteDTO.setTelefono("telefono");

        when(mockClienteRepository.findById(0)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> clienteServiceUnderTest.actualizarCliente(clienteDTO))
                .isInstanceOf(RecursoNoEncontradoException.class);
    }

    @Test
    void testGuardarCliente() {

        final ClienteDTO cliente = new ClienteDTO();
        cliente.setIdCliente(0);
        cliente.setNombre("nombre");
        cliente.setDireccion("direccion");
        cliente.setTelefono("telefono");

        final Cliente cliente1 = new Cliente(0, "nombre", "direccion", "telefono");
        when(mockModelMapper.map(any(Object.class), eq(Cliente.class))).thenReturn(cliente1);

        final Cliente cliente2 = new Cliente(0, "nombre", "direccion", "telefono");
        when(mockClienteRepository.save(new Cliente(0, "nombre", "direccion", "telefono"))).thenReturn(cliente2);

        final ClienteDTO result = clienteServiceUnderTest.guardarCliente(cliente);

    }

    @Test
    void testEliminarClientePorId() {

        final Optional<Cliente> cliente = Optional.of(new Cliente(0, "nombre", "direccion", "telefono"));
        when(mockClienteRepository.findById(0)).thenReturn(cliente);


        final ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdCliente(0);
        clienteDTO.setNombre("nombre");
        clienteDTO.setDireccion("direccion");
        clienteDTO.setTelefono("telefono");
        when(mockModelMapper.map(new Cliente(0, "nombre", "direccion", "telefono"), ClienteDTO.class))
                .thenReturn(clienteDTO);

        // Run the test
        clienteServiceUnderTest.eliminarClientePorId(0);

        verify(mockClienteRepository).deleteById(0);
    }

    @Test
    void testEliminarClientePorId_ClienteRepositoryFindByIdReturnsAbsent() {

        when(mockClienteRepository.findById(0)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> clienteServiceUnderTest.eliminarClientePorId(0))
                .isInstanceOf(RecursoNoEncontradoException.class);
    }
}
