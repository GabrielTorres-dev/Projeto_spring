package com.example.demo.services;

import com.example.demo.exceptions.ResourceNotFound;
import com.example.demo.models.dto.EspacoFormDTO;
import com.example.demo.models.entities.Espaco;
import com.example.demo.models.entities.Filial;
import com.example.demo.repositories.EspacoRepository;
import com.example.demo.repositories.FilialRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspacoService {

    private final EspacoRepository espacoRepository;
    private final FilialRepository filialRepository;

    public EspacoService(EspacoRepository espacoRepository,
                         FilialRepository filialRepository) {

        this.espacoRepository = espacoRepository;
        this.filialRepository = filialRepository;
    }

    public List<Espaco> listar() {
        return espacoRepository.findAll();
    }

    public Espaco buscarPorId(Long id) {

        return espacoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Espaço não encontrado."));
    }

    public Espaco cadastrar(EspacoFormDTO dto) {

        Filial filial = filialRepository.findById(dto.getFilialId())
                .orElseThrow(() -> new ResourceNotFound("Filial não encontrada."));

        Espaco espaco = new Espaco();

        BeanUtils.copyProperties(dto, espaco);

        espaco.setFilial(filial);

        return espacoRepository.save(espaco);
    }

    public void excluir(Long id) {

        Espaco espaco = buscarPorId(id);

        espacoRepository.delete(espaco);
    }
}