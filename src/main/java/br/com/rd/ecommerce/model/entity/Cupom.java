package br.com.rd.ecommerce.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "tb_cupom")
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_cupom")
    private Long codCupom;

    @NotNull
    @Column(name = "ds_nome", unique = true)
    private String nome;

    @NotNull
    @Column(name = "pc_desconto")
    private Double desconto;

    @NotNull
    @Column(name = "bl_ativo")
    private Boolean ativo;
}
