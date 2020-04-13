package br.com.rd.ecommerce.model.dto;


import br.com.rd.ecommerce.model.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoacaoDTO {
    private Long codDoacao;
    private Date dtDoacao;
    private String localDoacao;
    private BigDecimal vlDoacao;
    private Status status;
    private String dsFormaPagto;
    private Long codCliente;

}
