package Grupo_Endfield.EndfieldV2.Modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "reserva_servicio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaServicio {

    @EmbeddedId
    private ReservaServicioId id;

    @ManyToOne
    @MapsId("idReserva")
    @JoinColumn(name = "id_reserva", nullable = false)
    private Reserva reserva;

    @ManyToOne
    @MapsId("idServicio")
    @JoinColumn(name = "id_servicio", nullable = false)
    private Servicio servicio;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "fecha_servicio")
    private LocalDate fechaServicio;
}