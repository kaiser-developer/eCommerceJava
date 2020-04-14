package br.com.rd.ecommerce.controller;

import br.com.rd.ecommerce.model.dto.StatusFaleConoscoDTO;
import br.com.rd.ecommerce.service.StatusFaleConoscoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class StatusFaleConoscoController {
    @Autowired
    StatusFaleConoscoService service;

    @PostMapping("cadastrar-status-fale-conosco")
    public ResponseEntity cadastrarStatusFL(@RequestBody StatusFaleConoscoDTO statusFaleConoscoDTO){
        return service.cadastrarStatusFL(statusFaleConoscoDTO);
    }


}
