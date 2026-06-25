package Grupo_Endfield.EndfieldV2.Modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "visitante")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visitante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_visitante")
    private Integer idVisitante;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "primer_apellido", nullable = false, length = 100)
    private String primerApellido;

    @Column(name = "segundo_apellido", length = 100)
    private String segundoApellido;

    @Column(name = "rut", nullable = false, length = 12, unique = true)
    private String rut;

    @Column(name = "dv", nullable = false, length = 1)
    private String dv;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "sexo", length = 20)
    private String sexo;

    @Column(name = "correo", nullable = false, length = 150)
    private String correo;
}