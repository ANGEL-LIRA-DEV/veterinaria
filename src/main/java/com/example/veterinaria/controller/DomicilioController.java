package com.example.veterinaria.controller;

import com.example.veterinaria.dto.ApiResponse;
import com.example.veterinaria.model.DomicilioModel;
import com.example.veterinaria.service.DomicilioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.veterinaria.util.UtilConstants.*;

@RestController
@RequestMapping("/api/domicilios")
@RequiredArgsConstructor
public class DomicilioController {

    private final DomicilioService service;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> guardar(@RequestBody DomicilioModel domicilio){
        return ResponseEntity.ok(new ApiResponse<>(true, OKINSERT, service.guardar(domicilio)));
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
    public ResponseEntity<ApiResponse<?>> actualizar(@PathVariable Integer id, @RequestBody DomicilioModel domicilio){
        return ResponseEntity.ok(new ApiResponse<>(true, OKUPDATE, service.actualizar(id, domicilio)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> eliminar(@PathVariable Integer id){
        service.borrar(id);
        return ResponseEntity.ok(new ApiResponse<>(true, OKDEL, null));
    }

}
