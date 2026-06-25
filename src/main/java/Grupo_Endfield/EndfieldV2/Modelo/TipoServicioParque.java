package Grupo_Endfield.EndfieldV2.Modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_servicio_parque")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoServicioParque {

    @EmbeddedId
    private TipoServicioParqueId id;

    @ManyToOne
    @MapsId("idTipoServicio")
    @JoinColumn(name = "id_tipo_servicio", nullable = false)
    private TipoServicio tipoServicio;

    @ManyToOne
    @MapsId("idTipoParque")
    @JoinColumn(name = "id_tipo_parque", nullable = false)
    private TipoParque tipoParque;
}