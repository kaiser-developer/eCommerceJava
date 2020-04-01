package br.com.rd.ecommerce.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tb_pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_pedido")
    private Long codPedido;

    @Column(name = "dt_pedido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtPedido;

    @Column(name = "vl_pedido")
    private BigDecimal vlPedido;

    @Column(name = "vlFrete")
    private BigDecimal vlFrete;

    @Column(name = "ds_form_pagto")
    private String dsFormaPagto;

    @NotNull
    @Column(name = "cod_cliente")
    private Long codCliente;

    @NotNull
    @JoinColumn(name = "cod_endereco")
    private Long codEndereco;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cod_status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "cod_cupom")
    private Cupom cupom;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cod_pedido")
    private List<ItemPedido> itens;

    @Column(name = "dt_entrega")
    private Date dataEntrega;
}
