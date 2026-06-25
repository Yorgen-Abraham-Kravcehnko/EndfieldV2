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
public class TipoServicioParqueId implements Serializable {

    private Integer idTipoServicio;
    private Integer idTipoParque;
}