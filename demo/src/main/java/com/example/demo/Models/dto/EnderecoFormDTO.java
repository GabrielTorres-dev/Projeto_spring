package com.example.demo.Models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EnderecoFormDTO {

    @NotBlank
    private String rua;

    @NotBlank
    private String numero;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    private String estado;

    @NotBlank
    private String cep;
}