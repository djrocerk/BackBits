package Prueba.apibanco.service;

import Prueba.apibanco.dto.ClienteDTO;
import Prueba.apibanco.model.Cliente;
import ch.qos.logback.core.net.server.Client;

import java.util.List;

public interface IClienteService {
    public List<ClienteDTO> listarCliente();

    public ClienteDTO buscarClientePorId(Integer id_cliente);

    public ClienteDTO actualizarCliente(ClienteDTO clienteDT);

    public ClienteDTO guardarCliente(ClienteDTO cliente);

    public void eliminarClientePorId(Integer id_cliente);
}
