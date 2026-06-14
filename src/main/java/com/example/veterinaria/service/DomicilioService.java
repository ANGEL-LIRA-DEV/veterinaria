package com.example.veterinaria.service;

import com.example.veterinaria.model.DomicilioModel;
import com.example.veterinaria.repository.DomicilioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.veterinaria.util.UtilConstants.*;

@Service
@RequiredArgsConstructor
public class DomicilioService {

    private final DomicilioRepository repository;

    public DomicilioModel guardar(DomicilioModel domicilio){ return repository.save(domicilio); }

    public List<DomicilioModel> listar(){
        return repository.findByEstado(ACTIVO);
    }

    public DomicilioModel buscarPorId(Integer id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException(FAILQUERY));
    }

    public DomicilioModel actualizar(Integer id, DomicilioModel request){

        DomicilioModel domicilio = buscarPorId(id);

        domicilio
                .setCalle(request.getCalle())
                .setNumeroExterior(request.getNumeroExterior())
                .setColonia(request.getColonia())
                .setCiudad(request.getCiudad())
                .setEstadoGeografico(request.getEstadoGeografico())
                .setCodigoPostal(request.getCodigoPostal());

        return repository.save(domicilio);

    }

    public void borrar(Integer id){

        DomicilioModel domicilio = buscarPorId(id);
        domicilio.setEstado(INACTIVO);

        repository.save(domicilio);

    }

}
