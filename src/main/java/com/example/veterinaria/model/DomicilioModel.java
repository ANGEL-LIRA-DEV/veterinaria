package com.example.veterinaria.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "domicilios")
@Data
@Accessors(chain = true)
public class DomicilioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_domicilios")
    @SequenceGenerator(name = "seq_domicilios", sequenceName = "seq_domicilios", allocationSize = 1)
    @Column(name = "id_domicilio")
    private Integer idDomicilio;

    @ManyToOne
    @JoinColumn(name = "id_adoptante")
    private AdoptanteModel adoptante;

    @Column(name = "calle")
    private String calle;

    @Column(name = "numero_exterior")
    private Integer numeroExterior;

    @Column(name = "colonia")
    private String colonia;

    @Column(name = "ciudad")
    private String ciudad;

    @Column(name = "estado_geografico")
    private String estadoGeografico;

    @Column(name = "codigo_postal")
    private String codigoPostal;

    @Column(name = "estado")
    private Integer estado = 1;

}
