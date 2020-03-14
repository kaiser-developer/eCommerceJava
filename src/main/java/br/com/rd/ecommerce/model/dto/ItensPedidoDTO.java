package br.com.rd.ecommerce.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItensPedidoDTO {

    private Integer codigoPedido;
    private Integer quantidade;
    private Integer codProduto;

    public Integer getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(Integer codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(Integer codProduto) {
        this.codProduto = codProduto;
    }
}
