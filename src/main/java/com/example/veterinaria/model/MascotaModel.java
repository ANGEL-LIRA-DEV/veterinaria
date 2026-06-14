package com.example.veterinaria.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Entity
@Table(name = "mascotas")
@Data
@Accessors(chain = true)
public class MascotaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mascotas")
    @SequenceGenerator(name = "seq_mascotas", sequenceName = "seq_mascotas", allocationSize = 1)
    @Column(name = "id_mascota")
    private Integer idMascota;

    @ManyToOne
    @JoinColumn(name = "id_adoptante")
    private AdoptanteModel adoptante;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "especie")
    private String especie;

    @Column(name = "raza")
    private String raza;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "fecha_nacimiento")
    private LocalDateTime fechaNacimiento;

    @Column(name = "peso")
    private Float peso;

    @Column(name = "color")
    private String color;

    @Column(name = "estado")
    private Integer estado = 1;

}
