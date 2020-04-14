package br.com.rd.ecommerce.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoEmailDTO {
    private String nome;
    private String imagem;
    private BigDecimal preco;
    private Integer quantidade;
}
