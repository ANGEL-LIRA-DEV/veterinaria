package com.example.veterinaria.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Entity
@Table(name = "dispositivos")
@Data
@Accessors(chain = true)
public class DispositivoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_dispositivos")
    @SequenceGenerator(name = "seq_dispositivos", sequenceName = "seq_dispositivos", allocationSize = 1)
    @Column(name = "id_dispositivo")
    private Integer idDispositivo;

    @ManyToOne
    @JoinColumn(name = "id_mascota")
    private MascotaModel mascota;

    @Column(name = "numero_chip")
    private String numeroChip;

    @Column(name = "tipo_dispositivo")
    private String tipoDispositivo;

    @Column(name = "fecha_instalacion")
    private LocalDateTime fechaInstalacion =
            LocalDateTime.now();

    @Column(name = "estado")
    private Integer estado = 1;

}
