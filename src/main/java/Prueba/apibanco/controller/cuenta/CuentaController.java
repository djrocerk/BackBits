package Prueba.apibanco.controller.cuenta;

import Prueba.apibanco.dto.CuentaDTO;
import Prueba.apibanco.exception.RecursoNoEncontradoException;
import Prueba.apibanco.model.Cuenta;
import Prueba.apibanco.service.impl.CuentaService;
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
//localhost8080/api-app
@RequestMapping("api-app")
@CrossOrigin(value = "http://localhost:4200")
public class CuentaController {

    private static final Logger logger =
            LoggerFactory.getLogger(CuentaController.class);

    @Autowired
    private CuentaService cuentaService;

    //localhost8080/api-app/cuenta
    @GetMapping("/cuenta")
    public List<CuentaDTO> obtenerCuenta(){
        return this.cuentaService.listarCuenta();
    }

    @PostMapping("/cuenta")
    public CuentaDTO agregarCuenta(@RequestBody CuentaDTO cuenta){
        logger.info("cuenta a agregar" + cuenta);
        return this.cuentaService.guardarCuenta(cuenta);
    }

    @GetMapping("/cuenta/{id}")
    public CuentaDTO obtenerCuentaPorId(@PathVariable int id){
        var cuenta = this.cuentaService.buscarCuentaPorId(id);
        return cuenta;
    }

    @PutMapping("/cuenta")
    public ResponseEntity<CuentaDTO> actualizarCuenta(@RequestBody CuentaDTO cuentaRecibido){
        CuentaDTO cuentaActualizada = this.cuentaService.actualizarCuenta(cuentaRecibido);
        return ResponseEntity.ok(cuentaActualizada);
    }

    @DeleteMapping("/cuenta/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarCuenta(@PathVariable int id){
        cuentaService.eliminarCuentaPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
