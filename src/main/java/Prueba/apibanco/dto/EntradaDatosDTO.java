package Prueba.apibanco.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EntradaDatosDTO {
    private Integer id_cliente;
    @NotBlank
    private Date fechaInicio;
    private Date fechaFinal;
}
