package br.com.rd.ecommerce.controller;

import br.com.rd.ecommerce.model.dto.StatusDTO;
import br.com.rd.ecommerce.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @Autowired
    private StatusService service;

    @PostMapping("cadastrar-status")
    public ResponseEntity criarStatus(@RequestBody StatusDTO statusDTO){
        return service.cadastrarStatus(statusDTO);
    }
}
