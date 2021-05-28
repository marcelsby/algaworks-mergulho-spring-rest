package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public List<Cliente> listar() {
        var cliente1 = new Cliente();
        cliente1.setId(1);
        cliente1.setNome("João4");
        cliente1.setTelefone("34 99999-1111");
        cliente1.setEmail("joaodascouves@algaworks.com");

        var cliente2 = new Cliente();
        cliente2.setId(2);
        cliente2.setNome("Maria");
        cliente2.setTelefone("11 97777-3333");
        cliente2.setEmail("mariadasilva@algaworks.com");

        return Arrays.asList(cliente1, cliente2);
    }

}
