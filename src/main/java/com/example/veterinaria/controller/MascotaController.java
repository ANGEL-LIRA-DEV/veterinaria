package com.example.veterinaria.controller;

import com.example.veterinaria.dto.ApiResponse;
import com.example.veterinaria.model.AdoptanteModel;
import com.example.veterinaria.model.MascotaModel;
import com.example.veterinaria.service.MascotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.veterinaria.util.UtilConstants.*;

@RestController
@RequestMapping("/api/mascotas")
@RequiredArgsConstructor
public class MascotaController {

    private final MascotaService service;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> guardar(@RequestBody MascotaModel mascota){
        return ResponseEntity.ok(new ApiResponse<>(true, OKINSERT, service.guardar(mascota)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> listar(){
        return ResponseEntity.ok(new ApiResponse<>(true, OKQUERY, service.listar()));
    }

    @GetMapping("/adoptante/{idAdoptante}")
    public ResponseEntity<ApiResponse<?>> listarPorAdoptante(
            @PathVariable Integer idAdoptante){

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        OKQUERY,
                        service.listarPorAdoptante(idAdoptante)
                )
        );
    }

    @GetMapping("/vacunacion-pendiente")
    public ResponseEntity<ApiResponse<?>> listarVacunacionPendiente(){

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        OKQUERY,
                        service.listarVacunacionPendiente()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(new ApiResponse<>(true, OKQUERY, service.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> actualizar(@PathVariable Integer id, @RequestBody MascotaModel mascota){
        return ResponseEntity.ok(new ApiResponse<>(true, OKUPDATE, service.actualizar(id, mascota)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> eliminar(@PathVariable Integer id){
        service.borrar(id);
        return ResponseEntity.ok(new ApiResponse<>(true, OKDEL, null));
    }
    
}
