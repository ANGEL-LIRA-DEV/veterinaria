package com.example.veterinaria.controller;

import com.example.veterinaria.model.AdoptanteModel;
import com.example.veterinaria.service.AdoptanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.veterinaria.dto.ApiResponse;

import static com.example.veterinaria.util.UtilConstants.*;

@RestController
@RequestMapping("/api/adoptantes")
@RequiredArgsConstructor
public class AdoptanteController {

    private final AdoptanteService service;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> guardar(@RequestBody AdoptanteModel adoptante){
        return ResponseEntity.ok(new ApiResponse<>(true, OKINSERT , service.guardar(adoptante)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> listar(){
        return ResponseEntity.ok(new ApiResponse<>(true, OKQUERY, service.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(new ApiResponse<>(true, OKQUERY, service.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> actualizar(@PathVariable Integer id, @RequestBody AdoptanteModel adoptante){
        return ResponseEntity.ok(new ApiResponse<>(true, OKUPDATE, service.actualizar(id, adoptante)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> eliminar(@PathVariable Integer id){
        service.borrar(id);
        return ResponseEntity.ok(new ApiResponse<>(true, OKDEL, null));
    }

}
