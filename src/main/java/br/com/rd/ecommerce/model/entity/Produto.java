package br.com.rd.ecommerce.model.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "tb_produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_produto")
    private Long codProduto;


    @Column(name = "ds_produto", nullable = false)
    private String descricao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cod_categoria")
    private Categoria categoria;

    @NotNull
    @Column(name = "ds_qtd")
    private Integer qtdProduto;

    @NotNull
    @Column(name = "vl_produto")
    private BigDecimal valorProduto;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cod_produto")
    private List<Imagem> imagens;
}