package br.com.rd.ecommerce.model.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "tb_endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_endereco")
    private Long codEndereco;

    @NotNull
    @Column(name = "ds_destinatario")
    private String destinatario;

    @NotNull
    @Column(name = "ds_logarouro")
    private String logradouro;

    @NotNull
    @Column(name = "nr_numero")
    private Integer numero;

    @NotNull
    @Column(name = "ds_bairro")
    private String bairro;

    @Column(name = "complemento")
    private String complemento;

    @NotNull
    @Column(name = "ds_cidade")
    private String cidade;

    @NotNull
    @Column(name = "ds_estado")
    private String estado;

    @NotNull
    @Column(name = "ds_cep")
    private String cep;

    @NotNull
    @Column(name = "cod_cliente")
    private Long codCliente;
}
