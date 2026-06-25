package Grupo_Endfield.EndfieldV2.Modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "reserva")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Integer idReserva;

    @ManyToOne
    @JoinColumn(name = "id_visitante", nullable = false)
    private Visitante visitante;

    @ManyToOne
    @JoinColumn(name = "id_parque", nullable = false)
    private Parque parque;

    @Column(name = "cantidad_acompanantes", nullable = false)
    private Integer cantidadAcompanantes;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_termino", nullable = false)
    private LocalDate fechaTermino;

    @Column(name = "tipo_visita", nullable = false, length = 20)
    private String tipoVisita;

    @Column(name = "estado_reserva", nullable = false, length = 30)
    private String estadoReserva;
}