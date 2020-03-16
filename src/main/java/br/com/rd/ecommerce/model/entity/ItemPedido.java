package br.com.rd.ecommerce.model.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "tb_pedido_item")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_item_pedido")
    private Long codItemPedido;

    @NotNull
    @Column(name = "quantidade")
    private Integer quantidade;

    @NotNull
    @JoinColumn(name = "cod_produto")
    private Long codProduto;

    @Column(name = "cod_pedido")
    private Long codPedido;

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Long getCodItemPedido() {
        return codItemPedido;
    }

    public void setCodItemPedido(Long codItemPedido) {
        this.codItemPedido = codItemPedido;
    }

    public Long getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(Long codProduto) {
        this.codProduto = codProduto;
    }

    public Long getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(Long codPedido) {
        this.codPedido = codPedido;
    }
}
