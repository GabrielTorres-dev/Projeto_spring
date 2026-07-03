package com.example.demo.Controllers;

import com.example.demo.Models.Entities.Filial;
import com.example.demo.Models.dto.FilialFormDTO;
import com.example.demo.Services.FilialService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filiais")
public class FilialController {

    private final FilialService filialService;

    public FilialController(FilialService filialService) {
        this.filialService = filialService;
    }

    @GetMapping
    public ResponseEntity<List<Filial>> listar() {
        return ResponseEntity.ok(filialService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filial> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(filialService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Filial> cadastrar(@Valid @RequestBody FilialFormDTO dto) {
        return ResponseEntity.ok(filialService.cadastrar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filial> atualizar(@PathVariable Long id,
                                            @Valid @RequestBody FilialFormDTO dto) {
        return ResponseEntity.ok(filialService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        filialService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}