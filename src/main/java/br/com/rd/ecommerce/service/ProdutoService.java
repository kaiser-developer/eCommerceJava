package br.com.rd.ecommerce.service;

import br.com.rd.ecommerce.model.dto.ProdutoDTO;
import br.com.rd.ecommerce.model.entity.Imagem;
import br.com.rd.ecommerce.model.entity.Produto;
import br.com.rd.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.badRequest;

@Service("ProdutoService")
public class ProdutoService {

    @Autowired
    ProdutoRepository repository;

    public ResponseEntity salvarProduto(ProdutoDTO produtoDto){

        try {
            Produto produto = new Produto();
            produto.setCategoria(produtoDto.getCategoria());
            produto.setDescricao(produtoDto.getDescProduto());
            produto.setImagens(produtoDto.getImagens());
            produto.setQtdProduto(produtoDto.getQtdProduto());
            produto.setValorProduto(produtoDto.getValorProduto());

            return ResponseEntity.ok().body(repository.save(produto));
        }catch (Exception e){
            String erro = "Falha ao tentar cadastrar o produto";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity buscarProdutos(){
        try{
            return ResponseEntity.ok().body(repository.findAll());
        }catch (Exception e){
            String erro = "Não existem produtos";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity buscarProdutoId(Long id){
        try {
            return ResponseEntity.ok().body(repository.findById(id));
        }catch (Exception e){
            String erro = "Produto não está cadastrado no sistema";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity buscarProdutoTexto(String descricao){
        try {
            return ResponseEntity.ok().body(repository.findByDescricaoLike("%" + descricao + "%"));
        }catch (Exception e){
            String erro = "Não existe produtos com essa descrição";
            return badRequest().body(erro);
        }
    }
}
