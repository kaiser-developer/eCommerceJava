package br.com.rd.ecommerce.controller;

import br.com.rd.ecommerce.model.dto.ProdutoDTO;
import br.com.rd.ecommerce.model.entity.Produto;
import br.com.rd.ecommerce.repository.ProdutoRepository;
import br.com.rd.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProdutoController {

    @Autowired
    ProdutoService service;
    ProdutoRepository repository;

    @PostMapping("cadastrar-produto")
    public ResponseEntity cadastrarProduto(@RequestBody ProdutoDTO produtoDTO){
        return service.salvarProduto(produtoDTO);
    }

    @PostMapping("cadastrar-produtos")
    public ResponseEntity cadastrarProdutos(@RequestBody List<ProdutoDTO> produtosDTO){
        List<Produto> produtos = new ArrayList<>();
        produtosDTO.forEach(produtoDTO -> {
             service.salvarProduto(produtoDTO);
        });
        return ResponseEntity.ok().body("produtos");
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
    @PatchMapping("atualizar-produto/{id}")
    public ResponseEntity atualizarProduto(@PathVariable ("id") Long codProduto, @RequestBody ProdutoDTO produtoDTO) {
        return service.atualizarProduto(codProduto, produtoDTO);
    }
    @DeleteMapping("deletar-produto/{id}")
    public ResponseEntity deletarProduto(@PathVariable ("id") Long codProduto){
        return service.deletarProduto(codProduto);
    }

    @GetMapping("buscar-produtos/recomendados/{id}")
    public ResponseEntity produtosRecomendados(@PathVariable("id") Long id){
        return service.produtosRecomendados(id);
    }

    @GetMapping("buscar-produtos/categoria/{idProduto}/{idCategoria}")
    public ResponseEntity produtosCategoria(@PathVariable("idProduto") Long idProduto,
                                               @PathVariable("idCategoria") Long idCategoria){
        return service.produtosCategoria(idProduto, idCategoria);
    }
}
