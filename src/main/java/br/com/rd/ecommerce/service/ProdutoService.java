package br.com.rd.ecommerce.service;

import br.com.rd.ecommerce.model.dto.ProdutoDTO;
import br.com.rd.ecommerce.model.entity.ItemPedido;
import br.com.rd.ecommerce.model.entity.Produto;
import br.com.rd.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;

@Service("ProdutoService")
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public ResponseEntity salvarProduto(ProdutoDTO produtoDto) {

        try {
            Produto produto = new Produto();
            produto.setCategoria(produtoDto.getCategoria());
            produto.setDescricao(produtoDto.getDescProduto());
            produto.setImagens(produtoDto.getImagens());
            produto.setQtdProduto(produtoDto.getQtdProduto());
            produto.setValorProduto(produtoDto.getValorProduto());

            return ResponseEntity.ok().body(repository.save(produto));
        } catch (Exception e) {
            String erro = "Falha ao tentar cadastrar o produto";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity buscarProdutos() {
        try {
            return ResponseEntity.status(200).body(repository.findAll());
        } catch (Exception e) {
            String erro = "Não existem produtos";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity buscarProdutoId(Long id) {
        try {
            return ResponseEntity.ok().body(repository.findById(id));
        } catch (Exception e) {
            String erro = "Produto não está cadastrado no sistema";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity buscarProdutoTexto(String descricao) {
        try {
            return ResponseEntity.ok().body(repository.findByDescricaoLike("%" + descricao + "%"));
        } catch (Exception e) {
            String erro = "Não existe produtos com essa descrição";
            return badRequest().body(erro);
        }
    }

    public void atualizarEstoque(List<ItemPedido> itens) {
        itens.forEach(itemPedido -> {
            repository.findById(itemPedido.getCodProduto()).map(
                    produto -> {
                        produto.setQtdProduto(produto.getQtdProduto() - itemPedido.getQuantidade());
                        repository.save(produto);
                        return null;
                    }
            );
        });
    }

    public ResponseEntity atualizarProduto(Long codProduto, ProdutoDTO produtoDTO) {
        try {
            return repository.findById(codProduto).map(registro -> {
                registro.setQtdProduto(produtoDTO.getQtdProduto());
                registro.setValorProduto(produtoDTO.getValorProduto());
                Produto atualizado = repository.save(registro);
                return ResponseEntity.ok().body(atualizado);
            }).orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            String erro = "Erro ao tentar atualizar o produto";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity deletarProduto(Long codProduto) {
        try {
            return repository.findById(codProduto).map(registro -> {
                repository.deleteById(codProduto);
                return ResponseEntity.ok().build();
            }).orElse(ResponseEntity.notFound().build());
        }catch (Exception e){
            String erro = "Erro ao tentar deletar produto";
            return ResponseEntity.badRequest().body(erro);
        }
    }
}
