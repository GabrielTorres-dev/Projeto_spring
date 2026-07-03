package com.example.demo.Models.dto;

import com.example.demo.Models.dto.EnderecoFormDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioFormDTO {

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String senha;

    @NotBlank
    private String telefone;

    @Valid
    private EnderecoFormDTO endereco;
}