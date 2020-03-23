package br.com.rd.ecommerce.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "tb_funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_funcionario")
    private Long id;

    @NotNull
    @Column(name = "ds_matricula", unique = true)
    private String matricula;

    @NotNull
    @Column(name = "ds_name")
    private String nome;

    @NotNull
    @Column(name = "ds_senha")
    private String senha;

    @NotNull
    @Column(name = "vl_admin")
    private boolean admin;
}
