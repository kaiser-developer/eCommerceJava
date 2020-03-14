package br.com.rd.ecommerce.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "tb_imagem")
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_imagem")
    private Long codImagem;

    @Column(name = "ds_local")
    private String local;

    @Column(name = "cod_produto")
    private Long codProduto;

    public Long getCodImagem() {
        return codImagem;
    }

    public void setCodImagem(Long codImagem) {
        this.codImagem = codImagem;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
