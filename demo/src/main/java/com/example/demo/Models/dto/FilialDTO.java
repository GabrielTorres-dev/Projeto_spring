package com.example.demo.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilialDTO {

    private Long id;
    private String nome;
    private String cidade;
    private String estado;
}