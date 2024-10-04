package com.example.lab6_20212591.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="peliculas_directores")

public class PeliculasDirectores {


    //Se utilizará serializable con un entity aparte para esta Id
    @EmbeddedId
    private PeliculasDirectoresId id;

    @MapsId("peliculaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "peliculaId", nullable = false)
    private Pelicula pelicula;

    @MapsId("directorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "directorId", nullable = false)
    private Director director;
}
