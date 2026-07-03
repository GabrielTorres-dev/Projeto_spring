package com.example.demo.Controllers;

import com.example.demo.Models.dto.ReservaDTO;
import com.example.demo.Models.dto.ReservaFormDTO;
import com.example.demo.Services.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> listar() {
        return ResponseEntity.ok(reservaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ReservaDTO> criar(@RequestBody ReservaFormDTO dto) {
        return ResponseEntity.ok(reservaService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaDTO> atualizar(@PathVariable Long id,
                                                @RequestBody ReservaFormDTO dto) {
        return ResponseEntity.ok(reservaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        reservaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/resumo")
    public ResponseEntity<?> resumo() {
        return ResponseEntity.ok(reservaService.resumo());
    }
}