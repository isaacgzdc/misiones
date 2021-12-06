package com.example.misiones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.misiones.model.Planet;

@Repository
public interface PlanetRepository extends JpaRepository<Planet,Long>{

}
