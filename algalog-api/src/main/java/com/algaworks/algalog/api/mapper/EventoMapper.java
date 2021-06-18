package com.algaworks.algalog.api.mapper;

import com.algaworks.algalog.api.model.response.EventoOutput;
import com.algaworks.algalog.domain.model.Evento;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EventoMapper {

    private ModelMapper modelMapper;

    public EventoOutput toModel(Evento evento) {
        return modelMapper.map(evento, EventoOutput.class);
    }

    public List<EventoOutput> toCollectionModel(List<Evento> eventos) {
        return eventos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
