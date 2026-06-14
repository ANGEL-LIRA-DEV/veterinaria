package com.example.veterinaria.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Entity
@Table(name = "vacunas")
@Data
@Accessors(chain = true)
public class VacunaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vacunas")
    @SequenceGenerator(name = "seq_vacunas", sequenceName = "seq_vacunas", allocationSize = 1)
    @Column(name = "id_vacuna")
    private Integer idVacuna;

    @ManyToOne
    @JoinColumn(name = "id_mascota")
    private MascotaModel mascota;

    @Column(name = "nombre_vacuna")
    private String nombreVacuna;

    @Column(name = "fecha_aplicacion")
    private LocalDateTime fechaAplicacion =
            LocalDateTime.now();

    @Column(name = "fecha_proxima_dosis")
    private LocalDateTime fechaProximaDosis;

    @Column(name = "veterinario_responsable")
    private String veterinarioResponsable;

    @Column(name = "estado")
    private Integer estado = 1;

}
