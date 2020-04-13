package br.com.rd.ecommerce.model.dto;

import br.com.rd.ecommerce.model.entity.Endereco;
import br.com.rd.ecommerce.model.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEmailDTO {
    private List<ProdutoEmailDTO> produtos;
    private String nomeCliente;
    private String email;
    private Endereco endereco;
    private Long codPedido;
    private Date dataPedido;
    private Date entrega;
    private BigDecimal valorTotal;
    private BigDecimal valorFrete;
    private BigDecimal subTotal;
    private Status status;
    private BigDecimal desconto;
}
