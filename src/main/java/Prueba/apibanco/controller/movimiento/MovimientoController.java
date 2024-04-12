package Prueba.apibanco.controller.movimiento;

import Prueba.apibanco.controller.cuenta.CuentaController;
import Prueba.apibanco.dto.MovimientoDTO;
import Prueba.apibanco.exception.RecursoNoEncontradoException;
import Prueba.apibanco.exception.SaldoNegativoException;
import Prueba.apibanco.model.Movimiento;
import Prueba.apibanco.service.impl.MovimientoService;
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
public class MovimientoController {

    private static final Logger logger =
            LoggerFactory.getLogger(CuentaController.class);

    @Autowired
    private MovimientoService movimientoService;

    //localhost8080/api-app/movimiento
    @GetMapping("/movimiento")
    public List<MovimientoDTO> obtenerMovimiento(){
        return this.movimientoService.listarMovimiento();
    }

    @PostMapping("/movimiento")
    public ResponseEntity agregarMovimiento(@RequestBody MovimientoDTO movimiento) {
        try {
            logger.info("Movimiento agregado: " + movimiento);
            var movimientocrear = this.movimientoService.crearMovimiento(movimiento);
            return ResponseEntity.ok(movimientocrear);
        } catch (RecursoNoEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El saldo de la cuenta estaría en números rojos después de este movimiento.");
        }
    }

    @DeleteMapping("/movimiento/{idMovimiento}")
    public ResponseEntity<Map<String, Boolean>> eliminarMovimiento(@PathVariable int idMovimiento){
        var movimiento = movimientoService.buscarMovimientoPorId(idMovimiento);
        this.movimientoService.eliminarMovimientoPorId(movimiento.getId_movimineto());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado" ,Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }


}
