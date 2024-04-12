package Prueba.apibanco.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "cuenta")

public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column(name = "numero_cuenta", unique = true)
    private Integer numeroCuenta;

    @Column(name = "saldo")
    private Double saldo;

    @ManyToOne()
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;


}
