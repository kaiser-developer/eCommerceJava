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
    private Integer codPedido;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cod_pedido")
    private List<ItemPedido> itens;

    public Integer getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(Integer codPedido) {
        this.codPedido = codPedido;
    }

    public Date getDtPedido() {
        return dtPedido;
    }

    public void setDtPedido(Date dtPedido) {
        this.dtPedido = dtPedido;
    }

    public BigDecimal getVlPedido() {
        return vlPedido;
    }

    public void setVlPedido(BigDecimal vlPedido) {
        this.vlPedido = vlPedido;
    }

    public BigDecimal getVlFrete() {
        return vlFrete;
    }

    public void setVlFrete(BigDecimal vlFrete) {
        this.vlFrete = vlFrete;
    }

    public String getDsFormaPagto() {
        return dsFormaPagto;
    }

    public void setDsFormaPagto(String dsFormaPagto) {
        this.dsFormaPagto = dsFormaPagto;
    }

    public Long getCodEndereco() {
        return codEndereco;
    }

    public void setCodEndereco(Long codEndereco) {
        this.codEndereco = codEndereco;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Long codCliente) {
        this.codCliente = codCliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }
}
