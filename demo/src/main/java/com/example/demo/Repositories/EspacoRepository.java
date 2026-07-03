package com.example.demo.repositories;

import com.example.demo.Models.Entities.Espaco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspacoRepository extends JpaRepository<Espaco, Long> {
}