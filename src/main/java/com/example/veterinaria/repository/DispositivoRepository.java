package com.example.veterinaria.repository;

import com.example.veterinaria.model.DispositivoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DispositivoRepository extends JpaRepository<DispositivoModel, Integer> {

    List<DispositivoModel> findByEstado(Integer estado);

}
