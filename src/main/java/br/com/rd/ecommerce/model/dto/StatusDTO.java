package br.com.rd.ecommerce.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDTO {
    private Long cod_status;
    private String descricao;

    public Long getCod_status() {
        return cod_status;
    }

    public void setCod_status(Long cod_status) {
        this.cod_status = cod_status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
