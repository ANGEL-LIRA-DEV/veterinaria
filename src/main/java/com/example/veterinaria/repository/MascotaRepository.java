package com.example.veterinaria.repository;

import com.example.veterinaria.model.AdoptanteModel;
import com.example.veterinaria.model.MascotaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<MascotaModel, Integer> {

    List<MascotaModel> findByEstado(Integer estado);
    List<MascotaModel> findByAdoptanteAndEstado(
            AdoptanteModel adoptante,
            Integer estado
    );

    @Query("""
       SELECT m
       FROM MascotaModel m
       JOIN VacunaModel v
            ON v.mascota = m
       WHERE m.estado = :estado
       AND v.estado = :estado
       AND v.fechaProximaDosis <= :fechaLimite
       GROUP BY m
       ORDER BY MIN(v.fechaProximaDosis)
       """)
    List<MascotaModel> findMascotasConVacunacionPendiente(
            @Param("estado") Integer estado,
            @Param("fechaLimite") LocalDateTime fechaLimite
    );

}
