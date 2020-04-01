package br.com.rd.ecommerce.controller;

import br.com.rd.ecommerce.model.dto.EnderecoDTO;
import br.com.rd.ecommerce.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @GetMapping("enderecos/{idCliente}")
    public ResponseEntity buscarEnderecos(@PathVariable("idCliente") Long idCliente){
        return service.buscarEnderecos(idCliente);
    }

    @PostMapping("cadastrar-endereco")
    public ResponseEntity cadastrarEndereco(@RequestBody EnderecoDTO enderecoDTO){
        return service.cadastrarEndereco(enderecoDTO);
    }

    @GetMapping("endereco/{id}")
    public ResponseEntity buscarEndereco(@PathVariable("id") Long id){
        return service.buscarEndereco(id);
    }
}
