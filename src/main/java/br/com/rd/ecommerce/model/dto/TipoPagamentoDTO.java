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

    public Integer getCodTipoPgto() {
        return codTipoPgto;
    }

    public void setCodTipoPgto(Integer codTipoPgto) {
        this.codTipoPgto = codTipoPgto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }
}
