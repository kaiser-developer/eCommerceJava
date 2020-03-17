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
}
