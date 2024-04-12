package Prueba.apibanco.controller;

import Prueba.apibanco.dto.EntradaDatosDTO;
import Prueba.apibanco.dto.SalidaDatosDTO;
import Prueba.apibanco.service.IReporteService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/reporte")
@CrossOrigin(value = "http://localhost:4200")
public class ReporteController {

    private IReporteService iReporteService;
    public ReporteController(IReporteService iReporteService){
        this.iReporteService = iReporteService;
    }

    @PostMapping
    SalidaDatosDTO getData(@RequestBody EntradaDatosDTO entradaDatosDTO)throws ParseException {
        return iReporteService.generarReporte(entradaDatosDTO);

    }

}
