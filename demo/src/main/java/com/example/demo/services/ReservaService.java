package com.example.demo.Services;

import com.example.demo.Models.Entities.Espaco;
import com.example.demo.Models.Entities.Reserva;
import com.example.demo.Models.Entities.Usuario;
import com.example.demo.Models.dto.ReservaDTO;
import com.example.demo.Models.dto.ReservaFormDTO;
import com.example.demo.Repositories.EspacoRepository;
import com.example.demo.Repositories.ReservaRepository;
import com.example.demo.Repositories.UsuarioRepository;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.ResourceNotFound;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final EspacoRepository espacoRepository;

    public ReservaService(ReservaRepository reservaRepository,
                          UsuarioRepository usuarioRepository,
                          EspacoRepository espacoRepository) {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.espacoRepository = espacoRepository;
    }

    // LISTAR
    public List<ReservaDTO> listar() {
        return reservaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // BUSCAR POR ID
    public ReservaDTO buscarPorId(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Reserva não encontrada."));
        return toDTO(reserva);
    }

    // CRIAR RESERVA
    public ReservaDTO criar(ReservaFormDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFound("Usuário não encontrado."));

        Espaco espaco = espacoRepository.findById(dto.getEspacoId())
                .orElseThrow(() -> new ResourceNotFound("Espaço não encontrado."));

        // REGRA DE NEGÓCIO: conflito de horário
        boolean conflito = reservaRepository
                .existsByEspacoIdAndDataAndHoraInicioLessThanAndHoraFimGreaterThan(
                        espaco.getId(),
                        dto.getData(),
                        dto.getHoraFim(),
                        dto.getHoraInicio()
                );

        if (conflito) {
            throw new BusinessException("Já existe uma reserva para esse espaço nesse horário.");
        }

        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setEspaco(espaco);
        reserva.setData(dto.getData());
        reserva.setHoraInicio(dto.getHoraInicio());
        reserva.setHoraFim(dto.getHoraFim());

        return toDTO(reservaRepository.save(reserva));
    }

    // ATUALIZAR
    public ReservaDTO atualizar(Long id, ReservaFormDTO dto) {

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Reserva não encontrada."));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFound("Usuário não encontrado."));

        Espaco espaco = espacoRepository.findById(dto.getEspacoId())
                .orElseThrow(() -> new ResourceNotFound("Espaço não encontrado."));

        boolean conflito = reservaRepository
                .existsByEspacoIdAndDataAndHoraInicioLessThanAndHoraFimGreaterThan(
                        espaco.getId(),
                        dto.getData(),
                        dto.getHoraFim(),
                        dto.getHoraInicio()
                );

        if (conflito &&
                !(reserva.getEspaco().getId().equals(espaco.getId())
                        && reserva.getData().equals(dto.getData())
                        && reserva.getHoraInicio().equals(dto.getHoraInicio())
                        && reserva.getHoraFim().equals(dto.getHoraFim()))) {

            throw new BusinessException("Já existe uma reserva para esse espaço nesse horário.");
        }

        reserva.setUsuario(usuario);
        reserva.setEspaco(espaco);
        reserva.setData(dto.getData());
        reserva.setHoraInicio(dto.getHoraInicio());
        reserva.setHoraFim(dto.getHoraFim());

        return toDTO(reservaRepository.save(reserva));
    }

    // EXCLUIR
    public void excluir(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Reserva não encontrada."));
        reservaRepository.delete(reserva);
    }

    // CONVERSÃO ENTITY -> DTO
    private ReservaDTO toDTO(Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        dto.setData(reserva.getData());
        dto.setHoraInicio(reserva.getHoraInicio());
        dto.setHoraFim(reserva.getHoraFim());
        dto.setUsuario(reserva.getUsuario().getNome());
        dto.setEspaco(reserva.getEspaco().getNome());
        return dto;
    }
}
// RESUMO ESTATÍSTICO
public Object resumo() {

    List<Reserva> reservas = reservaRepository.findAll();

    int total = reservas.size();

    double duracaoMedia = reservas.stream()
            .mapToDouble(r -> java.time.Duration.between(r.getHoraInicio(), r.getHoraFim()).toHours())
            .average()
            .orElse(0);

    double receitaTotal = reservas.stream()
            .mapToDouble(r -> {
                long horas = java.time.Duration.between(r.getHoraInicio(), r.getHoraFim()).toHours();
                return horas * r.getEspaco().getValorHora();
            })
            .sum();

    return new java.util.HashMap<String, Object>() {{
        put("totalReservas", total);
        put("duracaoMediaHoras", duracaoMedia);
        put("receitaTotal", receitaTotal);
    }};
}