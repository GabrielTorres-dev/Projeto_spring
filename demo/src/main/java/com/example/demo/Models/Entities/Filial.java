package com.example.demo.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "filiais")
@Getter
@Setter
@NoArgsConstructor
public class Filial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cidade;

    private String estado;

    @OneToMany(mappedBy = "filial")
    private List<Espaco> espacos;
}