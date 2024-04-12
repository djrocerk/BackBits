package Prueba.apibanco.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_movimineto;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "valor")
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "numero_cuenta", referencedColumnName = "numero_cuenta")
    private Cuenta cuenta;

    @ManyToOne()
    @JoinColumn(name = "id_tipoMovimiento")
    private TipoMovimiento tipoMovimiento;


}
