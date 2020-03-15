package br.com.rd.ecommerce.controller;

import br.com.rd.ecommerce.model.dto.CategoriaDTO;
import br.com.rd.ecommerce.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriaController {

    @Autowired
    CategoriaService service;

    @PostMapping("criar-categoria")
    public ResponseEntity cadastrarCategoria(@RequestBody CategoriaDTO categoriaDTO){
        return service.salvarCategoria(categoriaDTO);
    }

    @GetMapping("buscar-categorias")
    public ResponseEntity buscarCategoria(){
        return service.buscarCategoria();
    }
}
