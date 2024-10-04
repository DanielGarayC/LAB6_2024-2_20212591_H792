package com.example.lab6_20212591.repository;

import com.example.lab6_20212591.entity.Director;
import com.example.lab6_20212591.entity.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {
}
