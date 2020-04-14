package br.com.rd.ecommerce.controller;

import br.com.rd.ecommerce.model.dto.FaleConoscoDTO;
import br.com.rd.ecommerce.service.FaleConoscoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FaleConoscoController {

    @Autowired
    private FaleConoscoService service;

    @PostMapping("cadastrar-fale-conosco")
    public ResponseEntity criarMensagem(@RequestBody FaleConoscoDTO FaleConoscoDTO){
        return service.cadastrarMensagem(FaleConoscoDTO);
    }
}