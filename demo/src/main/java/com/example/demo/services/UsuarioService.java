package com.example.demo.services;

import com.example.demo.Models.Entities.Endereco;
import com.example.demo.Models.Entities.Usuario;
import com.example.demo.Models.dto.EnderecoDTO;
import com.example.demo.Models.dto.UsuarioDTO;
import com.example.demo.Repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<UsuarioDTO> buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(this::toDTO);
    }

    public UsuarioDTO criar(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Já existe um usuário com esse email.");
        }
        Usuario salvo = usuarioRepository.save(usuario);
        return toDTO(salvo);
    }

    public Optional<UsuarioDTO> atualizar(Long id, Usuario dadosAtualizados) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNome(dadosAtualizados.getNome());
            usuario.setEmail(dadosAtualizados.getEmail());
            usuario.setTelefone(dadosAtualizados.getTelefone());

            if (dadosAtualizados.getSenha() != null && !dadosAtualizados.getSenha().isBlank()) {
                usuario.setSenha(dadosAtualizados.getSenha());
            }

            if (dadosAtualizados.getEndereco() != null) {
                usuario.setEndereco(dadosAtualizados.getEndereco());
            }

            Usuario atualizado = usuarioRepository.save(usuario);
            return toDTO(atualizado);
        });
    }

    public boolean deletar(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setTelefone(usuario.getTelefone());
        dto.setEndereco(toEnderecoDTO(usuario.getEndereco()));
        return dto;
    }

    private EnderecoDTO toEnderecoDTO(Endereco endereco) {
        if (endereco == null) {
            return null;
        }
        EnderecoDTO dto = new EnderecoDTO();
        dto.setId(endereco.getId());
        dto.setRua(endereco.getRua());
        dto.setNumero(endereco.getNumero());
        dto.setBairro(endereco.getBairro());
        dto.setCidade(endereco.getCidade());
        dto.setEstado(endereco.getEstado());
        dto.setCep(endereco.getCep());
        return dto;
    }
}