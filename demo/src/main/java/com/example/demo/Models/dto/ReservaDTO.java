package com.example.demo.Models.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservaDTO {

    private Long id;
    private String usuario;
    private String espaco;
    private LocalDate data;
    private LocalTime horaInicio;
    private LocalTime horaFim;
}