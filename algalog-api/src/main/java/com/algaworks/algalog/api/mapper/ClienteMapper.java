package com.algaworks.algalog.api.mapper;

import com.algaworks.algalog.api.model.request.ClienteInput;
import com.algaworks.algalog.api.model.response.ClienteOutput;
import com.algaworks.algalog.domain.model.Cliente;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ClienteMapper {

    ModelMapper modelMapper;

    public ClienteOutput toModel(Cliente cliente) {
        return modelMapper.map(cliente, ClienteOutput.class);
    }

    public List<ClienteOutput> toCollectionModel(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Cliente toEntity(ClienteInput cliente) {
        return modelMapper.map(cliente, Cliente.class);
    }

}
