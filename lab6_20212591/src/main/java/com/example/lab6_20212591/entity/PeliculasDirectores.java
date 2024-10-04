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

    @MapsId("eventoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "eventoId", nullable = false)
    private Pelicula pelicula;

    @MapsId("artistaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "artistaId", nullable = false)
    private Director director;
}
