package Prueba.apibanco.repository;

import Prueba.apibanco.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
    List<Movimiento> findByCuentaNumeroCuenta(Integer numeroCuenta);


    // metodo para calcular el total de credito
    @Query("SELECT COALESCE(SUM(m.valor), 0) FROM Movimiento m WHERE m.cuenta.numeroCuenta IN (SELECT c.numeroCuenta FROM Cuenta c WHERE c.cliente.id = :clienteId) AND m.tipoMovimiento.id = 1 AND m.fecha BETWEEN :fechaInicio AND :fechaFin")
    Double calcularTotalcre(@Param("clienteId") Integer clienteId, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

    // metodo para calcular el total de debito
    @Query("SELECT COALESCE(SUM(m.valor), 0) FROM Movimiento m WHERE m.cuenta.numeroCuenta IN (SELECT c.numeroCuenta FROM Cuenta c WHERE c.cliente.id = :clienteId) AND m.tipoMovimiento.id = 2 AND m.fecha BETWEEN :fechaInicio AND :fechaFin")
    Double calculartotaldeb(@Param("clienteId") Integer clienteId, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

}
