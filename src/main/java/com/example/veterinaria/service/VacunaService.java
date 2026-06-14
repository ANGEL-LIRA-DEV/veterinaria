package com.example.veterinaria.service;

import com.example.veterinaria.model.MascotaModel;
import com.example.veterinaria.model.VacunaModel;
import com.example.veterinaria.repository.MascotaRepository;
import com.example.veterinaria.repository.VacunaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.veterinaria.util.UtilConstants.*;

@Service
@RequiredArgsConstructor
public class VacunaService {

    private final VacunaRepository vacunaRepository;
    private final MascotaRepository mascotaRepository;

    public VacunaModel guardar(VacunaModel vacuna){

        if (vacuna.getMascota() == null ||
                vacuna.getMascota().getIdMascota() == null) {

            throw new RuntimeException(FALTAMASCOTA);
        }

        MascotaModel mascota =
                mascotaRepository.findById(
                        vacuna.getMascota().getIdMascota()
                ).orElseThrow(() ->
                        new RuntimeException(MASCOTANOEXISTE));

        vacuna.setMascota(mascota);

        return vacunaRepository.save(vacuna);
    }

    public List<VacunaModel> listar(){
        return vacunaRepository.findByEstado(ACTIVO);
    }

    public List<VacunaModel> listarPorMascota(Integer idMascota){

        MascotaModel mascota =
                mascotaRepository.findById(idMascota)
                        .orElseThrow(() ->
                                new RuntimeException("No existe"));

        return vacunaRepository.findByMascotaAndEstado(
                mascota,
                ACTIVO
        );
    }

    public VacunaModel buscarPorId(Integer id){

        return vacunaRepository.findById(id).orElseThrow(() -> new RuntimeException(FAILQUERY));

    }

    public VacunaModel actualizar(Integer id, VacunaModel request){

        VacunaModel vacuna = buscarPorId(id);
        vacuna
                .setNombreVacuna(request.getNombreVacuna())
                .setFechaAplicacion(request.getFechaAplicacion())
                .setFechaProximaDosis(request.getFechaProximaDosis())
                .setVeterinarioResponsable(request.getVeterinarioResponsable());

        return vacunaRepository.save(vacuna);

    }

    public void borrar(Integer id){

        VacunaModel vacuna = buscarPorId(id);
        vacuna.setEstado(INACTIVO);
        vacunaRepository.save(vacuna);

    }

}
