package com.example.misiones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.misiones.model.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger,Long>{

}
