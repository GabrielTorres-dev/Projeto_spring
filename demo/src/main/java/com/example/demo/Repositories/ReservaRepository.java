package com.example.demo.Repositories;

import com.example.demo.Models.Entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByEspacoIdAndData(Long espacoId, LocalDate data);

    boolean existsByEspacoIdAndDataAndHoraInicioLessThanAndHoraFimGreaterThan(
            Long espacoId,
            LocalDate data,
            LocalTime horaFim,
            LocalTime horaInicio
    );
}