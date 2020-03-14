package br.com.rd.ecommerce.model.entity;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "tb_status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_status")
    private Long cod_status;
    @NotNull
    @Column(name = "ds_descricao")
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
