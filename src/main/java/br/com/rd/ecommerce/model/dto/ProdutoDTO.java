package br.com.rd.ecommerce.model.dto;

import br.com.rd.ecommerce.model.entity.Categoria;
import br.com.rd.ecommerce.model.entity.Imagem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    private Long codProduto;
    private String descProduto;
    private Categoria categoria;
    private Integer qtdProduto;
    private BigDecimal valorProduto;
    private List<Imagem> imagens;
}
