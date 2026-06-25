package Grupo_Endfield.EndfieldV2.Modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "pago_efectivo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoEfectivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago_efectivo")
    private Integer idPagoEfectivo;

    @ManyToOne
    @JoinColumn(name = "id_pago", nullable = false)
    private Pago pago;

    @Column(name = "tipo_moneda", length = 50)
    private String tipoMoneda;

    @Column(name = "num_boleta", length = 100)
    private String numBoleta;

    @Column(name = "monto", nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;
}