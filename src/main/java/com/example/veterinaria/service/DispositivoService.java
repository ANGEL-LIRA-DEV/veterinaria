package com.example.veterinaria.service;

import com.example.veterinaria.model.DispositivoModel;
import com.example.veterinaria.repository.DispositivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.veterinaria.util.UtilConstants.*;

@Service
@RequiredArgsConstructor
public class DispositivoService {

    private final DispositivoRepository repository;

    public DispositivoModel guardar(DispositivoModel dispositivo){ return repository.save(dispositivo); }

    public List<DispositivoModel> listar(){
        return repository.findByEstado(ACTIVO);
    }

    public DispositivoModel buscarPorId(Integer id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException(FAILQUERY));
    }

    public DispositivoModel actualizar(Integer id, DispositivoModel request){

        DispositivoModel dispositivo = buscarPorId(id);

        dispositivo
                .setNumeroChip(request.getNumeroChip())
                .setTipoDispositivo(request.getTipoDispositivo())
                .setFechaInstalacion(request.getFechaInstalacion());

        return repository.save(dispositivo);

    }

    public void borrar(Integer id){

        DispositivoModel dispositivo = buscarPorId(id);
        dispositivo.setEstado(INACTIVO);

        repository.save(dispositivo);

    }

}
