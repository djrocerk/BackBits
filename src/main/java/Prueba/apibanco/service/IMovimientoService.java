package Prueba.apibanco.service;

import Prueba.apibanco.dto.MovimientoDTO;
import Prueba.apibanco.model.Movimiento;

import java.util.List;

public interface IMovimientoService {


    public List<MovimientoDTO> listarMovimiento();

    public MovimientoDTO buscarMovimientoPorId(Integer idMovimiento);

    public MovimientoDTO crearMovimiento(MovimientoDTO movimiento);

    public void eliminarMovimientoPorId(Integer idMovimiento);
}
