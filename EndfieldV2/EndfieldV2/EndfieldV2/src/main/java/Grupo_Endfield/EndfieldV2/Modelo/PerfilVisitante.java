package Grupo_Endfield.EndfieldV2.Modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "perfil_visitante")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilVisitante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil_visitante")
    private Integer idPerfilVisitante;

    @ManyToOne
    @JoinColumn(name = "id_visitante", nullable = false)
    private Visitante visitante;

    @Column(name = "tipo_perfil", nullable = false, length = 100)
    private String tipoPerfil;

    @Column(name = "descripcion", length = 255)
    private String descripcion;
}