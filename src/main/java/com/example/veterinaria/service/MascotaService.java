package com.example.veterinaria.service;

import com.example.veterinaria.model.AdoptanteModel;
import com.example.veterinaria.model.MascotaModel;
import com.example.veterinaria.repository.AdoptanteRepository;
import com.example.veterinaria.repository.MascotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.veterinaria.util.UtilConstants.*;

@Service
@RequiredArgsConstructor
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final AdoptanteRepository adoptanteRepository;

    public MascotaModel guardar(MascotaModel mascota){

        if (mascota.getAdoptante() == null ||
                mascota.getAdoptante().getIdAdoptante() == null) {

            throw new RuntimeException(FALTAADOPTANTE);
        }

        AdoptanteModel adoptante =
                adoptanteRepository.findById(
                        mascota.getAdoptante().getIdAdoptante()
                ).orElseThrow(() ->
                        new RuntimeException(ADOPTANTENOEXISTE));

        mascota.setAdoptante(adoptante);

        return mascotaRepository.save(mascota);
    }

    public List<MascotaModel> listar(){
        return mascotaRepository.findByEstado(ACTIVO);
    }

    public List<MascotaModel> listarPorAdoptante(Integer idAdoptante){

        AdoptanteModel adoptante =
                adoptanteRepository.findById(idAdoptante)
                        .orElseThrow(() -> new RuntimeException("No existe"));

        return mascotaRepository.findByAdoptanteAndEstado(
                adoptante,
                ACTIVO
        );
    }

    public List<MascotaModel> listarVacunacionPendiente() {

        try {

            LocalDateTime fechaLimite =
                    LocalDateTime.now().plusDays(7);

            return mascotaRepository
                    .findMascotasConVacunacionPendiente(
                            ACTIVO,
                            fechaLimite
                    );

        } catch (Exception e) {

            throw new RuntimeException(
                    FAILPENDINGVACCINES,
                    e
            );
        }
    }

    public MascotaModel buscarPorId(Integer id){
        return mascotaRepository.findById(id).orElseThrow(() -> new RuntimeException(FAILQUERY));
    }

    public MascotaModel actualizar(Integer id, MascotaModel request){

        MascotaModel mascota = buscarPorId(id);

        mascota
                .setNombre(request.getNombre())
                .setEspecie(request.getEspecie())
                .setRaza(request.getRaza())
                .setSexo(request.getSexo())
                .setFechaNacimiento(request.getFechaNacimiento())
                .setPeso(request.getPeso())
                .setColor(request.getColor());

        return mascotaRepository.save(mascota);

    }

    public void borrar(Integer id){

        MascotaModel mascota = buscarPorId(id);
        mascota.setEstado(INACTIVO);

        mascotaRepository.save(mascota);

    }

}
