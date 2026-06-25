package Grupo_Endfield.EndfieldV2.Modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "pago_banco")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoBanco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago_banco")
    private Integer idPagoBanco;

    @ManyToOne
    @JoinColumn(name = "id_pago", nullable = false)
    private Pago pago;

    @ManyToOne
    @JoinColumn(name = "id_banco", nullable = false)
    private Banco banco;

    @Column(name = "num_boleta", length = 100)
    private String numBoleta;

    @Column(name = "monto", nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;
}