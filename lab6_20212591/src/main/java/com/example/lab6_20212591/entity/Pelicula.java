package com.example.lab6_20212591.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="peliculas")

public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "peliculaid", nullable = false)
    private Integer id;

    @Column(name= "titulo", length = 100)
    @Size(min=3, max = 100, message = "El titulo debe tener entre 3 y 100 caracteres")
    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @Column(name= "fechaestreno")
    @NotBlank(message = "La fecha de estreno es obligatoria")
    @PastOrPresent(message = "La fecha de estreno no puede ser una fecha a futuro")
    private Date fechaestreno;

    @Column(name= "duracionminutos")
    @NotBlank(message = "La duración de la película es obligatoria")
    @Positive(message = "La duración de la película (en minutos) debe ser un número positivo")
    private Integer duracionminutos;

}
