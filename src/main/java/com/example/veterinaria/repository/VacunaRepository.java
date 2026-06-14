package com.example.veterinaria.repository;

import com.example.veterinaria.model.MascotaModel;
import com.example.veterinaria.model.VacunaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacunaRepository extends JpaRepository<VacunaModel, Integer> {

    List<VacunaModel> findByEstado(Integer estado);

    List<VacunaModel> findByMascotaAndEstado(
            MascotaModel mascota,
            Integer estado
    );

}
