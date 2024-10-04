package com.example.lab6_20212591.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @Size(min = 2,max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Column(name= "telefono", length = 9)
    @Size(min = 9,max = 9, message = "El teléfono debe tener 9 dígitos")
    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "\\d+", message = "El teléfono debe contener solo dígitos")
    private String telefono;

    @Column(name= "nacionalidad", length = 50)
    @NotBlank(message = "La nacionalidad es obligatoria")
    private String nacionalidad;

}
