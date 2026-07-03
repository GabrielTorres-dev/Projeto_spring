package com.example.demo.Controllers;

import com.example.demo.Models.Entities.Espaco;
import com.example.demo.Models.dto.EspacoFormDTO;
import com.example.demo.Services.EspacoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/espacos")
public class EspacoController {

    private final EspacoService espacoService;

    public EspacoController(EspacoService espacoService) {
        this.espacoService = espacoService;
    }

    @GetMapping
    public ResponseEntity<List<Espaco>> listar() {
        return ResponseEntity.ok(espacoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Espaco> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(espacoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Espaco> cadastrar(@Valid @RequestBody EspacoFormDTO dto) {
        return ResponseEntity.ok(espacoService.cadastrar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        espacoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}