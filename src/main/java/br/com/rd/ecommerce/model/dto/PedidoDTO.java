package br.com.rd.ecommerce.model.dto;

import br.com.rd.ecommerce.model.entity.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Long codPedido;
    private Date dtPedido;
    private BigDecimal vlPedido;
    private BigDecimal vlFrete;
    private String dsFormaPagto;
    private Long codEndereco;
    private Long codCliente;
    private List<ItemPedidoDTO> itensPedido;

    public Long getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Long codCliente) {
        this.codCliente = codCliente;
    }

    public Long getCodEndereco() {
        return codEndereco;
    }

    public void setCodEndereco(Long codEndereco) {
        this.codEndereco = codEndereco;
    }

    public List<ItemPedidoDTO> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedidoDTO> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public Long getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(Long codPedido) {
        this.codPedido = codPedido;
    }

    public Date getDtPedido() {
        return dtPedido;
    }

    public void setDtPedido(Date dtPedido) {
        this.dtPedido = dtPedido;
    }

    public BigDecimal getVlPedido() {
        return vlPedido;
    }

    public void setVlPedido(BigDecimal vlPedido) {
        this.vlPedido = vlPedido;
    }

    public BigDecimal getVlFrete() {
        return vlFrete;
    }

    public void setVlFrete(BigDecimal vlFrete) {
        this.vlFrete = vlFrete;
    }

    public String getDsFormaPagto() {
        return dsFormaPagto;
    }

    public void setDsFormaPagto(String dsFormaPagto) {
        this.dsFormaPagto = dsFormaPagto;
    }
}
