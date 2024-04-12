package Prueba.apibanco.service.impl;

import Prueba.apibanco.dto.ClienteDTO;
import Prueba.apibanco.exception.RecursoNoEncontradoException;
import Prueba.apibanco.model.Cliente;
import Prueba.apibanco.repository.ClienteRepository;
import Prueba.apibanco.service.IClienteService;
import ch.qos.logback.core.net.server.Client;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService implements IClienteService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<ClienteDTO> listarCliente() {
        var clientes = this.clienteRepository.findAll();
        return clientes.stream()
                .map(cliente -> this.modelMapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO buscarClientePorId(Integer id) {
       var cliente = this.clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado"));
        return this.modelMapper.map(cliente, ClienteDTO.class);
    }

    @Override
    public ClienteDTO actualizarCliente(ClienteDTO clienteDTO) {

        var clienteEncontrado = clienteRepository.findById(clienteDTO.getIdCliente())
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado"));

        clienteEncontrado.setNombre(clienteDTO.getNombre());
        clienteEncontrado.setDireccion(clienteDTO.getDireccion());
        clienteEncontrado.setTelefono(clienteDTO.getTelefono());

        var clienteActualizado = clienteRepository.save(clienteEncontrado);
        return modelMapper.map(clienteActualizado, ClienteDTO.class);
    }

    @Override
    public ClienteDTO guardarCliente(ClienteDTO cliente) {
        var clienteagregar = this.clienteRepository.save(modelMapper.map(cliente, Cliente.class));
        return  modelMapper.map(clienteagregar, ClienteDTO.class);
    }

    @Override
    public void eliminarClientePorId(Integer id_cliente) {
        ClienteDTO clientedb = buscarClientePorId(id_cliente);
        this.clienteRepository.deleteById(clientedb.getIdCliente());
    }
}
