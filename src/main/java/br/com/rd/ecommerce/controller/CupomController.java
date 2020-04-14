package br.com.rd.ecommerce.controller;

import br.com.rd.ecommerce.model.dto.CupomDTO;
import br.com.rd.ecommerce.model.entity.Cupom;
import br.com.rd.ecommerce.service.CupomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CupomController {

    @Autowired
    private CupomService service;

    @PostMapping("cadastrar-cupom")
    public ResponseEntity cadastrarCupom(@RequestBody CupomDTO cupomDTO){
        return service.cadastrarCupom(cupomDTO);
    }

    @PostMapping("buscar-cupom")
    public ResponseEntity buscarCupom(@RequestBody CupomDTO cupomDTO){
        return service.buscarCupom(cupomDTO);
    }

    @GetMapping("buscar-todos-cupons")
    public ResponseEntity buscartodosCupons(){
        return service.buscarTodosCupons();
    }

    @GetMapping("buscar-cupons-cliente/{codCliente}")
    public ResponseEntity buscarCuponsCliente(@PathVariable("codCliente") Long codCliente){
        return service.buscarCuponsCliente(codCliente);
    }
}
