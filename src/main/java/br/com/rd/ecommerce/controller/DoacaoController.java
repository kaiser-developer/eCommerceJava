package br.com.rd.ecommerce.controller;

import br.com.rd.ecommerce.model.dto.DoacaoDTO;
import br.com.rd.ecommerce.service.DoacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DoacaoController {

    @Autowired
    private DoacaoService service;

    @PostMapping("cadastrar-doacao")
    public ResponseEntity cadastrarDoacao(@RequestBody DoacaoDTO doacaoDTO) {
        return service.cadastrarDoacao(doacaoDTO);
    }

    @GetMapping("buscar-doacao/{id}")
    public ResponseEntity buscarDoacao(@PathVariable ("id") Long codCliente) {
        return service.buscarDoacao(codCliente);
    }

    @PatchMapping("/cancelar-doacao/{id}")
    public ResponseEntity cancelarDoacao(@PathVariable ("id") Long codDoacao) {
        return service.cancelarDoacao(codDoacao);
    }
}
