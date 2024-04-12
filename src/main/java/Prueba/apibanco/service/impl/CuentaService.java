package Prueba.apibanco.service.impl;

import Prueba.apibanco.dto.ClienteDTO;
import Prueba.apibanco.dto.CuentaDTO;
import Prueba.apibanco.exception.RecursoNoEncontradoException;
import Prueba.apibanco.model.Cuenta;
import Prueba.apibanco.repository.CuentaRepository;
import Prueba.apibanco.service.ICuentaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuentaService implements ICuentaService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public List<CuentaDTO> listarCuenta() {
        var cuentas = this.cuentaRepository.findAll();
        return cuentas.stream()
                .map(cliente -> this.modelMapper.map(cliente, CuentaDTO.class))
                .collect(Collectors.toList());
    }
    /*@Override
    public CuentaDTO obtenerCuentaPorNumeroCuenta(Integer numeroCuenta) {
       var cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta);
       return modelMapper.map(cuenta, CuentaDTO.class);
    }*/

    @Override
    public CuentaDTO buscarCuentaPorId(Integer idCuenta) {
        var cuenta = this.cuentaRepository.findById(idCuenta).orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado"));
        return modelMapper.map(cuenta, CuentaDTO.class);
    }

    @Override
    public CuentaDTO actualizarCuenta(CuentaDTO cuentaDT) {
        var cuentaEncontrada = cuentaRepository.findById(cuentaDT.getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Cuenta no encontrada"));

        cuentaEncontrada.setNumeroCuenta(cuentaDT.getNumeroCuenta());
        cuentaEncontrada.setSaldo(cuentaDT.getSaldo());
        cuentaEncontrada.setCliente(cuentaDT.getCliente());

        var cuentaActualizada = cuentaRepository.save(cuentaEncontrada);
        return modelMapper.map(cuentaActualizada, CuentaDTO.class);
    }


    @Override
    public CuentaDTO guardarCuenta(CuentaDTO cuenta) {
        var cuentaagregar = this.cuentaRepository.save(modelMapper.map(cuenta, Cuenta.class));
        return modelMapper.map(cuentaagregar, CuentaDTO.class);
    }

    @Override
    public void eliminarCuentaPorId(Integer idCuenta) {
        CuentaDTO cuentadb = buscarCuentaPorId(idCuenta);
        if (cuentadb == null) {
            throw new RecursoNoEncontradoException("La cuenta con el ID " + idCuenta + " no se encontró.");
        }
        this.cuentaRepository.deleteById(idCuenta);
    }
}
