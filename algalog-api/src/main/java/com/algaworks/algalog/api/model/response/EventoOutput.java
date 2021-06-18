package com.algaworks.algalog.api.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class EventoOutput {

    private Long id;
    private String descricao;
    private OffsetDateTime dataRegistro;

}
