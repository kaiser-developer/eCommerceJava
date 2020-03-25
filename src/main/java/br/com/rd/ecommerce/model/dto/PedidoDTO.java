package br.com.rd.ecommerce.model.dto;

import br.com.rd.ecommerce.model.entity.Cupom;
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
    private Cupom cupom;
}
