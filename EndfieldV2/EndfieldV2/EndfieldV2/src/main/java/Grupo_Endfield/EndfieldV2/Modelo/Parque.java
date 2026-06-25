package Grupo_Endfield.EndfieldV2.Modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "parque")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parque")
    private Integer idParque;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "tipo_parque", nullable = false, length = 100)
    private String tipoParqueDesc;

    @ManyToOne
    @JoinColumn(name = "id_tipo_parque")
    private TipoParque tipoParque;

    @Column(name = "km2", precision = 10, scale = 2)
    private BigDecimal km2;

    @Column(name = "max_visitantes")
    private Integer maxVisitantes;

    @Column(name = "cap_estacionamiento")
    private Integer capEstacionamiento;

    @Column(name = "abierto_temporada", nullable = false)
    private Boolean abiertoTemporada;

    @Column(name = "temporadas", length = 255)
    private String temporadas;
    
}
