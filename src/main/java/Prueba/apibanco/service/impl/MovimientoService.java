package Prueba.apibanco.service.impl;

import Prueba.apibanco.dto.MovimientoDTO;
import Prueba.apibanco.exception.RecursoNoEncontradoException;
import Prueba.apibanco.model.Cuenta;
import Prueba.apibanco.model.Movimiento;
import Prueba.apibanco.model.TipoMovimiento;
import Prueba.apibanco.repository.CuentaRepository;
import Prueba.apibanco.repository.MovimientoRepository;
import Prueba.apibanco.service.IMovimientoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimientoService implements IMovimientoService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public List<MovimientoDTO> listarMovimiento() {
        return this.movimientoRepository.findAll()
                .stream()
                .map(movimiento -> this.modelMapper.map(movimiento, MovimientoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MovimientoDTO buscarMovimientoPorId(Integer idMovimiento) {
        var movimiento = this.movimientoRepository.findById(idMovimiento).orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado"));
        return modelMapper.map(movimiento, MovimientoDTO.class);
    }

    @Override
    public MovimientoDTO crearMovimiento(MovimientoDTO movimientoDTO) {
        Integer numeroCuenta = movimientoDTO.getCuenta().getNumeroCuenta();
        var cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta);

        if (cuenta == null) {
            throw new RecursoNoEncontradoException("La cuenta asociada al movimiento no existe");
        }

        double saldoActual = cuenta.getSaldo();
        double valorMovimiento = movimientoDTO.getValor();
        TipoMovimiento tipoMovimiento = movimientoDTO.getTipoMovimiento();

        if (tipoMovimiento.getIdTipoMovimiento() == 1){ // Si es crédito
            if (saldoActual - valorMovimiento < 0){
                throw new RecursoNoEncontradoException("El saldo de la cuenta sería negativo después del movimiento");
            }
            cuenta.setSaldo(saldoActual - valorMovimiento);
        }else {// Si es débito
            cuenta.setSaldo(saldoActual + valorMovimiento);
        }
        var movimiento = modelMapper.map(movimientoDTO, Movimiento.class);
        movimiento.setCuenta(cuenta);
        var movimientoGuardado = movimientoRepository.save(movimiento);
        cuentaRepository.save(cuenta);
        return modelMapper.map(movimientoGuardado, MovimientoDTO.class);
    }

    @Override
    public void eliminarMovimientoPorId(Integer idMovimiento) {

        Movimiento movimiento = this.movimientoRepository.findById(idMovimiento)
                .orElseThrow(() -> new RecursoNoEncontradoException("Movimiento no encontrado con ID: " + idMovimiento));

        var cuenta = movimiento.getCuenta();
        TipoMovimiento tipoMovimiento = movimiento.getTipoMovimiento();
        double valorMovimiento = movimiento.getValor();
        double saldoActual = cuenta.getSaldo();

        if (tipoMovimiento.getIdTipoMovimiento() == 1) { // Si es crédito
            cuenta.setSaldo(saldoActual + valorMovimiento);
        } else { // Si es débito
            cuenta.setSaldo(saldoActual - valorMovimiento);
        }
        cuentaRepository.save(cuenta);
        this.movimientoRepository.deleteById(idMovimiento);
    }
}
