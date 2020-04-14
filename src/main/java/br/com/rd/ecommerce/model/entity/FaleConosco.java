package br.com.rd.ecommerce.model.entity;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "tb_Fale_conosco")
public class FaleConosco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "cod_Fale_conosco")
    private Long codFaleConosco;

    @NotNull
    @Column (name = "ds_nome_completo")
    private String nomeCompleto;

    @NotNull
    @Column (name = "ds_telefone" )
    private String telefone;

    @NotNull
    @Column (name = "ds_email")
    private String email;

    @NotNull
    @Column (name = "ds_mensagem")
    private String mensagem;

    @Column (name = "cod_cliente")
    private Long codCliente;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cod_status")
    private StatusFaleConosco statusFL;

}
