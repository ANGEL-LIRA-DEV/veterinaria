package com.example.veterinaria.repository;

import com.example.veterinaria.model.DomicilioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DomicilioRepository extends JpaRepository<DomicilioModel, Integer> {

    List<DomicilioModel> findByEstado(Integer estado);

}
