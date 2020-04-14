package br.com.rd.ecommerce.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tb_doacao")
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_doacao")
    private Long codDoacao;

    @Column(name = "dt_doacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtDoacao;

    @NotNull
    @Column(name = "ds_local_doacao")
    private String localDoacao;

    @NotNull
    @Column(name = "vl_doacao")
    private BigDecimal vlDoacao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cod_status")
    private Status status;
    
    @Column(name = "ds_form_pagto_doacao")
    private String dsFormaPagto;

    @NotNull
    @Column(name = "cod_cliente")
    private Long codCliente;
}
