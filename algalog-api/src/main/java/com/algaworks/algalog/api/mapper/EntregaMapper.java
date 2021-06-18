package com.algaworks.algalog.api.mapper;

import com.algaworks.algalog.api.model.response.EntregaOutput;
import com.algaworks.algalog.domain.model.Entrega;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EntregaMapper {

    private ModelMapper modelMapper;

    public EntregaOutput toModel(Entrega entrega) {
        return modelMapper.map(entrega, EntregaOutput.class);
    }

    public List<EntregaOutput> toCollectionModel(List<Entrega> entregas) {
        return entregas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Entrega toEntity(com.algaworks.algalog.api.model.request.EntregaInput entregaInput) {
        return modelMapper.map(entregaInput, Entrega.class);
    }

}
