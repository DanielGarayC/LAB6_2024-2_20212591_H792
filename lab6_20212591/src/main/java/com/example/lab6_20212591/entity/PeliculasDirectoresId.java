package com.example.lab6_20212591.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;


//Proceso para la tabla que relaciones peliculas y directores x.x
@Getter
@Setter
@Embeddable
public class PeliculasDirectoresId implements Serializable {

    private static final long serialVersionUID = 3585358035461845792L;
    @Column(name = "peliculaId", nullable = false)
    private Integer peliculaId;

    @Column(name = "directorId", nullable = false)
    private Integer directorId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PeliculasDirectoresId entity = (PeliculasDirectoresId) o;
        return Objects.equals(this.peliculaId, entity.peliculaId) &&
                Objects.equals(this.directorId, entity.directorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(peliculaId, directorId);
    }
}
