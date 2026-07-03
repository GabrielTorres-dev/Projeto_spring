package com.example.demo.services;

import com.example.demo.exceptions.ResourceNotFound;
import com.example.demo.models.dto.FilialDTO;
import com.example.demo.models.dto.FilialFormDTO;
import com.example.demo.models.entities.Filial;
import com.example.demo.repositories.FilialRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilialService {

    private final FilialRepository filialRepository;

    public FilialService(FilialRepository filialRepository) {
        this.filialRepository = filialRepository;
    }

    public List<Filial> listar() {
        return filialRepository.findAll();
    }

    public Filial buscarPorId(Long id) {
        return filialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Filial não encontrada."));
    }

    public Filial cadastrar(FilialFormDTO dto) {

        Filial filial = new Filial();
        BeanUtils.copyProperties(dto, filial);

        return filialRepository.save(filial);
    }

    public Filial atualizar(Long id, FilialFormDTO dto) {

        Filial filial = buscarPorId(id);

        BeanUtils.copyProperties(dto, filial);

        return filialRepository.save(filial);
    }

    public void excluir(Long id) {

        Filial filial = buscarPorId(id);

        filialRepository.delete(filial);
    }
}