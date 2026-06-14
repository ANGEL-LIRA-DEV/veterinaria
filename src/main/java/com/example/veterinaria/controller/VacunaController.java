package com.example.veterinaria.controller;

import com.example.veterinaria.dto.ApiResponse;
import com.example.veterinaria.model.VacunaModel;
import com.example.veterinaria.service.VacunaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.veterinaria.util.UtilConstants.*;
import static com.example.veterinaria.util.UtilConstants.OKDEL;

@RestController
@RequestMapping("/api/vacunas")
@RequiredArgsConstructor
public class VacunaController {

    private final VacunaService service;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> guardar(@RequestBody VacunaModel vacuna){
        return ResponseEntity.ok(new ApiResponse<>(true, OKINSERT, service.guardar(vacuna)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> listar(){
        return ResponseEntity.ok(new ApiResponse<>(true, OKQUERY, service.listar()));
    }

    @GetMapping("/mascota/{idMascota}")
    public ResponseEntity<ApiResponse<?>> listarPorMascota(
            @PathVariable Integer idMascota){

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        OKQUERY,
                        service.listarPorMascota(idMascota)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(new ApiResponse<>(true, OKQUERY, service.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> actualizar(@PathVariable Integer id, @RequestBody VacunaModel vacuna){
        return ResponseEntity.ok(new ApiResponse<>(true, OKUPDATE, service.actualizar(id, vacuna)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> eliminar(@PathVariable Integer id){
        service.borrar(id);
        return ResponseEntity.ok(new ApiResponse<>(true, OKDEL, null));
    }

}
