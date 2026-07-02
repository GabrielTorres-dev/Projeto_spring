package com.example.demo.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FilialFormDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String cidade;

    @NotBlank
    private String estado;
}