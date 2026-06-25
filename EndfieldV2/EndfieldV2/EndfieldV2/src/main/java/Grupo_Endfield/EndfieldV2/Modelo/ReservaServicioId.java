package Grupo_Endfield.EndfieldV2.Modelo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaServicioId implements Serializable {

    private Integer idReserva;
    private Integer idServicio;
}