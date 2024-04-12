package Prueba.apibanco.service;

import Prueba.apibanco.dto.EntradaDatosDTO;
import Prueba.apibanco.dto.SalidaDatosDTO;

import java.text.ParseException;

public interface IReporteService {

    SalidaDatosDTO generarReporte(EntradaDatosDTO entradaDatosDTO) throws ParseException;


}
