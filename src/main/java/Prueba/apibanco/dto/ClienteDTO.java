package Prueba.apibanco.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

    private Integer idCliente;
    private String nombre;
    private String direccion;
    private String telefono;

}
