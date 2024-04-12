package Prueba.apibanco.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TipoMovimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoMovimiento;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nombre")
    private String nombre;

}
