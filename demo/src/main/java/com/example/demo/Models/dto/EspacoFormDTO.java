package com.example.demo.Models.dto;

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

    @Data
    public static class FilialDTO {

        private Long id;
        private String nome;
        private String cidade;
        private String estado;
    }
}