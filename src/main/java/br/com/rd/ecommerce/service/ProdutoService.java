package br.com.rd.ecommerce.service;

import br.com.rd.ecommerce.model.dto.ProdutoDTO;
import br.com.rd.ecommerce.model.entity.ItemPedido;
import br.com.rd.ecommerce.model.entity.Produto;
import br.com.rd.ecommerce.repository.ProdutoRepository;
import org.hibernate.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;

@Service("ProdutoService")
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;
    @PersistenceContext
    private EntityManager em;

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

    public ResponseEntity produtosRecomendados(Long codProduto){
        try {
            List<Produto> produtos = null;
            String sql =
                    new StringBuffer()
                            .append("SELECT p.* FROM TB_PRODUTO p ")
                            .append("INNER JOIN ")
                            .append("(SELECT ip.cod_produto, SUM(ip.quantidade) AS qtd ")
                            .append("FROM TB_PEDIDO_ITEM ip ")
                            .append("INNER JOIN (SELECT item.COD_PEDIDO FROM TB_PEDIDO_ITEM item ")
                            .append("WHERE item.COD_PRODUTO = " + codProduto + ") aux ")
                            .append("ON aux.cod_pedido = ip.cod_pedido ")
                            .append("GROUP BY ip.cod_produto ORDER BY 2 DESC) ")
                            .append("MAIS_VENDIDOS ON (MAIS_VENDIDOS.cod_produto = p.cod_produto) ")
                            .append("WHERE p.cod_produto <> " + codProduto + " LIMIT 4").toString();

            Query query = em.createNativeQuery(sql, Produto.class);
            produtos = query.getResultList();
            return ResponseEntity.ok().body(produtos);
        } catch (QueryException e){
            String erro = "Erro ao buscar produtos";
            return ResponseEntity.badRequest().body(erro + e.getMessage());
        }
    }

    public ResponseEntity produtosCategoria(Long codProduto){
        try {
            List<Produto> produtos = null;
            String sql =
                    new StringBuffer()
                            .append("SELECT p.* FROM TB_PRODUTO p ")
                            .append("LEFT JOIN (SELECT tp.cod_produto, sum(tp.quantidade) as quantidade ")
                            .append("FROM TB_PEDIDO_ITEM tp GROUP BY tp.COD_PRODUTO) aux ")
                            .append("ON aux.COD_PRODUTO = p.COD_PRODUTO ")
                            .append("INNER JOIN (SELECT pro_aux.COD_CATEGORIA ")
                            .append("FROM TB_PRODUTO pro_aux WHERE pro_aux.COD_PRODUTO = " + codProduto + ") aux2 ")
                            .append("ON p.COD_CATEGORIA = aux2.COD_CATEGORIA ")
                            .append("WHERE  p.COD_PRODUTO <> " + codProduto + " ORDER BY aux.quantidade DESC LIMIT 4").toString();

            Query query = em.createNativeQuery(sql, Produto.class);
            produtos = query.getResultList();
            return ResponseEntity.ok().body(produtos);
        } catch (QueryException e){
            String erro = "Erro ao buscar produtos";
            return ResponseEntity.badRequest().body(erro + e.getMessage());
        }
    }

    public List<Produto> produtosPedido(Long codPedido){
        List<Produto> produtos = null;

        String sql =
                new StringBuffer()
                        .append("SELECT p.* FROM  TB_PRODUTO p ")
                        .append("INNER JOIN ")
                        .append("(SELECT tpi.COD_PRODUTO FROM tb_pedido_item tpi WHERE tpi.COD_PEDIDO = "+ codPedido +") tb_aux ")
                        .append("ON p.COD_PRODUTO = tb_aux.COD_PRODUTO").toString();
        Query query = em.createNativeQuery(sql, Produto.class);
        produtos = query.getResultList();
        return produtos;
    }

    public ResponseEntity produtosMaisVendidos(){
        List<Produto> produtos = null;

        String sql =
                new StringBuffer()
                        .append("SELECT p.* FROM TB_PRODUTO p ")
                        .append("INNER JOIN (SELECT ip.cod_produto, SUM(ip.quantidade) AS qtd FROM TB_PEDIDO_ITEM ip GROUP BY ip.cod_produto ORDER BY 2 DESC) ")
                        .append("MAIS_VENDIDOS ON (MAIS_VENDIDOS.cod_produto = p.cod_produto) LIMIT 4").toString();
        Query query = em.createNativeQuery(sql, Produto.class);
        produtos = query.getResultList();
        return ResponseEntity.ok().body(produtos);
    }
}
