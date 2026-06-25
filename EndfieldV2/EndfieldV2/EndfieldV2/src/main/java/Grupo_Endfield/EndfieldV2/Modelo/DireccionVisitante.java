package Grupo_Endfield.EndfieldV2.Modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "direccion_visitante")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DireccionVisitante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion_visitante")
    private Integer idDireccionVisitante;

    @ManyToOne
    @JoinColumn(name = "id_visitante", nullable = false)
    private Visitante visitante;

    @ManyToOne
    @JoinColumn(name = "id_comuna", nullable = false)
    private Comuna comuna;

    @Column(name = "calle", nullable = false, length = 255)
    private String calle;

    @Column(name = "numero", length = 20)
    private String numero;

    @Column(name = "referencia", length = 255)
    private String referencia;
}