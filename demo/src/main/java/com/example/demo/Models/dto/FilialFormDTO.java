package com.example.demo.Models.dto;

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