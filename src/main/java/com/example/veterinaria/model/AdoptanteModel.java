package com.example.veterinaria.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Entity
@Table(name = "adoptantes")
@Data
@Accessors(chain = true)
public class AdoptanteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_adoptantes")
    @SequenceGenerator(name = "seq_adoptantes", sequenceName = "seq_adoptantes", allocationSize = 1)
    @Column(name = "id_adoptante")
    private Integer idAdoptante;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "correo")
    private String correo;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro =
            LocalDateTime.now();

    @Column(name = "estado")
    private Integer estado = 1;

}
