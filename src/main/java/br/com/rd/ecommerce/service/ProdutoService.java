package br.com.rd.ecommerce.service;

import br.com.rd.ecommerce.model.dto.ProdutoDTO;
import br.com.rd.ecommerce.model.entity.Imagem;
import br.com.rd.ecommerce.model.entity.Produto;
import br.com.rd.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("ProdutoService")
public class ProdutoService {

    @Autowired
    ProdutoRepository repository;

    public ResponseEntity salvarProduto(ProdutoDTO produtoDto){

        Produto produto = new Produto();
        produto.setCategoria(produtoDto.getCategoria());
        produto.setDescricao(produtoDto.getDescProduto());
        produto.setImagens(produtoDto.getImagens());
        produto.setQtdProduto(produtoDto.getQtdProduto());
        produto.setValorProduto(produtoDto.getValorProduto());

        return ResponseEntity.ok().body(repository.save(produto));
    }

    public List<Produto> buscarProdutos(){
        return repository.findAll();
    }

    public Optional<Produto> buscarProdutoId(Long id){
        return repository.findById(id);
    }
}
