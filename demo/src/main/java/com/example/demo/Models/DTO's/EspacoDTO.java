package com.example.demo.models.dto;

import lombok.Data;

@Data
public class EspacoDTO {

    private Long id;
    private String nome;
    private String tipo;
    private Double valorHora;
    private String filial;
}