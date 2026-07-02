package com.example.demo.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "espacos")
@Getter
@Setter
@NoArgsConstructor
public class Espaco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipo; // Sala de reunião, Mesa de trabalho...

    @Column(nullable = false)
    private Double valorHora;

    @ManyToOne
    @JoinColumn(name = "filial_id", nullable = false)
    private Filial filial;

    @OneToMany(mappedBy = "espaco")
    private List<Reserva> reservas;
}