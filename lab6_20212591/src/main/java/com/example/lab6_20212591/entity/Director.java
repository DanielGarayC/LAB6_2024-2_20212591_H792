package com.example.lab6_20212591.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="directores")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "directorid", nullable = false)
    private Integer id;

    @Column(name= "nombre", length = 100)
    private String nombre;

    @Column(name= "telefono", length = 9)
    private String telefono;

    @Column(name= "nacionalidad", length = 50)
    private String nacionalidad;

}
