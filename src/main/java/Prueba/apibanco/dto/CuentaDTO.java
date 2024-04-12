package Prueba.apibanco.dto;

import Prueba.apibanco.model.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaDTO {
        private Integer id;
        private Integer numeroCuenta;
        private Double saldo;
        private Cliente cliente;
}
