package Prueba.apibanco.dto;

import Prueba.apibanco.model.Cuenta;
import Prueba.apibanco.model.TipoMovimiento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MovimientoDTO {

    private Integer id_movimineto;
    private Date fecha;
    private Double valor;
    private Cuenta cuenta;
    private TipoMovimiento tipoMovimiento;

}
