package br.com.rd.ecommerce.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@Entity
@Table(name = "tb_doacao_item")
public class ItemDoado {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "cod_item_doado")
        private Long codItemDoado;

        @NotNull
        @Column(name = "quantidade_doada")
        private Integer quantidadeDoada;

        @NotNull
        @JoinColumn(name = "cod_produto")
        private Long codProdutoDoacao;

        @Column(name = "cod_doacao")
        private Long codDoacao;
 }

