package com.example.demo.services;

import com.example.demo.Models.dto.EnderecoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EnderecoService {

    private final List<EnderecoDTO> enderecos = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1L);

    public List<EnderecoDTO> listarTodos() {
        return enderecos;
    }

    public Optional<EnderecoDTO> buscarPorId(Long id) {
        return enderecos.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public EnderecoDTO criar(EnderecoDTO enderecoDTO) {
        enderecoDTO.setId(nextId.getAndIncrement());
        enderecos.add(enderecoDTO);
        return enderecoDTO;
    }

    public Optional<EnderecoDTO> atualizar(Long id, EnderecoDTO enderecoDTO) {
        return buscarPorId(id).map(endereco -> {
            endereco.setRua(enderecoDTO.getRua());
            endereco.setNumero(enderecoDTO.getNumero());
            endereco.setBairro(enderecoDTO.getBairro());
            endereco.setCidade(enderecoDTO.getCidade());
            endereco.setEstado(enderecoDTO.getEstado());
            endereco.setCep(enderecoDTO.getCep());
            return endereco;
        });
    }

    public boolean deletar(Long id) {
        return enderecos.removeIf(e -> e.getId().equals(id));
    }
}