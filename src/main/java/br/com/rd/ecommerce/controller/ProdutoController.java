package br.com.rd.ecommerce.controller;

import br.com.rd.ecommerce.model.dto.ProdutoDTO;
import br.com.rd.ecommerce.model.entity.Produto;
import br.com.rd.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class ProdutoController {

    @Autowired
    ProdutoService service;

    @PostMapping("cadastrar-produto")
    public ResponseEntity cadastrarProduto(@RequestBody ProdutoDTO produtoDTO){
        return service.salvarProduto(produtoDTO);
    }

    @GetMapping("buscar-produto")
    public ResponseEntity buscarProdutos(){
        return service.buscarProdutos();
    }

    @GetMapping("buscar-produto/{id}")
    public ResponseEntity buscarProdutoId(@PathVariable("id") Long id){
        return service.buscarProdutoId(id);
    }

    @GetMapping("buscar-produto/filtro/{descricao}")
    public ResponseEntity buscarProdutoDescricao(@PathVariable("descricao") String descricao){
        return service.buscarProdutoTexto(descricao);
    }
}
