package Prueba.apibanco.service;

import Prueba.apibanco.dto.CuentaDTO;
import Prueba.apibanco.model.Cuenta;

import java.util.List;

public interface ICuentaService {
    public List<CuentaDTO> listarCuenta();

   // public CuentaDTO obtenerCuentaPorNumeroCuenta(Integer numeroCuenta);

    public  CuentaDTO actualizarCuenta(CuentaDTO cuentaDTO);

    public CuentaDTO buscarCuentaPorId(Integer idCuenta);

    public CuentaDTO guardarCuenta(CuentaDTO cuenta);

    public void eliminarCuentaPorId(Integer idCuenta);
}
