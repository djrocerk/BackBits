package Prueba.apibanco.repository;

import Prueba.apibanco.model.Cuenta;
import Prueba.apibanco.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
    public Cuenta findByNumeroCuenta(Integer numeroCuenta);


    @Query("SELECT c FROM Cuenta c WHERE c.cliente.id = :idCliente")
    List<Cuenta> findByClienteId(Integer idCliente);



}
