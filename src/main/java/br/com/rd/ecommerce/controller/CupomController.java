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

    @DeleteMapping("deletar-cupom/{id}")
    public ResponseEntity deletarCupom(@PathVariable("id") Long id){return service.deletarCupom(id);}


    @PatchMapping("atualizar-cupom/{id}")
    public ResponseEntity atualizarCupom(@PathVariable("id")Long id,@RequestBody CupomDTO cupomDTO){return service.atualizarCupom(id, cupomDTO);}

    @GetMapping("buscar-todos-cupons")
    public ResponseEntity buscartodosCupons(){
        return service.buscarTodosCupons();
    }

    @GetMapping("filtrar-cupons/{id}")
    public ResponseEntity filtrarCupons(@PathVariable ("id")Long codCliente){return service.filtrarCupom(codCliente);}
}