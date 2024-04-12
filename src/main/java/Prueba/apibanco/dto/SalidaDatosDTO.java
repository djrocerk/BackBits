package Prueba.apibanco.dto;

import Prueba.apibanco.model.Cuenta;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SalidaDatosDTO {
    @NotEmpty
    private Integer id_cliente;
    private List<Cuenta> cuentas;
    private Double totaldebito;
    private Double totalcredito;


}
