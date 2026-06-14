package com.example.veterinaria.service;

import com.example.veterinaria.model.AdoptanteModel;
import com.example.veterinaria.repository.AdoptanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.veterinaria.util.UtilConstants.*;

@Service
@RequiredArgsConstructor
public class AdoptanteService {

    private final AdoptanteRepository repository;

    public AdoptanteModel guardar(AdoptanteModel adoptante){ return repository.save(adoptante); }

    public List<AdoptanteModel> listar(){
        return repository.findByEstado(ACTIVO);
    }

    public AdoptanteModel buscarPorId(Integer id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException(FAILQUERY));
    }

    public AdoptanteModel actualizar(Integer id, AdoptanteModel request){

        AdoptanteModel adoptante = buscarPorId(id);

        adoptante
                .setNombre(request.getNombre())
                .setApellidoPaterno(request.getApellidoPaterno())
                .setApellidoMaterno(request.getApellidoMaterno())
                .setTelefono(request.getTelefono())
                .setCorreo(request.getTelefono())
                .setFechaRegistro(request.getFechaRegistro());

        return repository.save(adoptante);

    }

    public void borrar(Integer id){

        AdoptanteModel adoptante = buscarPorId(id);
        adoptante.setEstado(INACTIVO);
        repository.save(adoptante);

    }

}
