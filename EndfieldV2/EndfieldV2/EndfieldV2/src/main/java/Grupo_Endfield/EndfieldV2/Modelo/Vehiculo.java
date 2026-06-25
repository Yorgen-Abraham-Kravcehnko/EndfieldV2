package Grupo_Endfield.EndfieldV2.Modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehiculo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private Integer idVehiculo;

    @ManyToOne
    @JoinColumn(name = "id_visitante", nullable = false)
    private Visitante visitante;

    @Column(name = "patente", nullable = false, length = 10)
    private String patente;

    @Column(name = "marca", length = 100)
    private String marca;

    @Column(name = "modelo", length = 100)
    private String modelo;

    @Column(name = "color", length = 50)
    private String color;

    @Column(name = "tipo_vehiculo", length = 50)
    private String tipoVehiculo;
    
}