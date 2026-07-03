package com.example.demo.Models.Entities;

import java.util.List;

import com.example.demo.Models.Entities.Filial;
import com.example.demo.Models.Entities.Reserva;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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