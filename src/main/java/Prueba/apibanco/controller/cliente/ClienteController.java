package Prueba.apibanco.controller.cliente;

import Prueba.apibanco.dto.ClienteDTO;
import Prueba.apibanco.exception.RecursoNoEncontradoException;
import Prueba.apibanco.model.Cliente;
import Prueba.apibanco.service.impl.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//http://localhost:8080/api-app
@RequestMapping("api-app")
@CrossOrigin(value = "http://localhost:4200")
public class ClienteController {

    private static final Logger logger =
            LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteService clienteService;

    //http://localhost:8080/api-app/cliente
    @GetMapping("/cliente")
    public List<ClienteDTO> obtenerCliente() {
        return this.clienteService.listarCliente();
    }

    @PostMapping("/cliente")
    public ClienteDTO agregarCliente(@RequestBody ClienteDTO cliente){
        var clientellegada = this.clienteService.guardarCliente(cliente);
        logger.info("Cliente a agregar" + cliente);
        return clientellegada;
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable int id){
        return ResponseEntity.ok(this.clienteService.buscarClientePorId(id));
    }


    @PutMapping("/cliente")
    public ResponseEntity<ClienteDTO> actualizarCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteActualizado = clienteService.actualizarCliente(clienteDTO);
        return ResponseEntity.ok(clienteActualizado);
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity eliminarCliente(@PathVariable int id){
        clienteService.eliminarClientePorId(id);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
