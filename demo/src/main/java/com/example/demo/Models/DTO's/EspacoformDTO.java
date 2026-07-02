package com.example.demo.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EspacoFormDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String tipo;

    @NotNull
    private Double valorHora;

    @NotNull
    private Long filialId;
}