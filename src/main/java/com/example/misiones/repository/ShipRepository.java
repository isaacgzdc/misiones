package com.example.misiones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.misiones.model.Ship;

@Repository
public interface ShipRepository extends JpaRepository<Ship,Long>{

}
