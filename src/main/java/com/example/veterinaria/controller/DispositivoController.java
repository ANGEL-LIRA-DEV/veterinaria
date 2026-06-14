package com.example.veterinaria.controller;

import com.example.veterinaria.dto.ApiResponse;
import com.example.veterinaria.model.DispositivoModel;
import com.example.veterinaria.service.DispositivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.veterinaria.util.UtilConstants.*;

@RestController
@RequestMapping("/api/dispositivos")
@RequiredArgsConstructor
public class DispositivoController {

    private final DispositivoService service;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> guardar(@RequestBody DispositivoModel dispositivo){
        return ResponseEntity.ok(new ApiResponse<>(true, OKINSERT, service.guardar(dispositivo)));
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
    public ResponseEntity<ApiResponse<?>> actualizar(@PathVariable Integer id, @RequestBody DispositivoModel dispositivo){
        return ResponseEntity.ok(new ApiResponse<>(true, OKUPDATE, service.actualizar(id, dispositivo)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> eliminar(@PathVariable Integer id){
        service.borrar(id);
        return ResponseEntity.ok(new ApiResponse<>(true, OKDEL, null));
    }

}
