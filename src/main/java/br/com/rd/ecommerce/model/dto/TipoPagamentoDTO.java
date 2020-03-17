package br.com.rd.ecommerce.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoPagamentoDTO {

    private Integer codTipoPgto;
    private String descricao;
    private Integer parcelas;
}
