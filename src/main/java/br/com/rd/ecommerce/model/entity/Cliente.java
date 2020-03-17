package br.com.rd.ecommerce.model.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "tb_cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_cliente")
    private Long codCliente;

    @NotNull
    @Column(name = "ds_name")
    private String nome;

    @NotNull
    @Column(unique = true, name = "ds_cpf")
    private String cpf;

    @NotNull
    @Column(name = "ds_telefone")
    private String telefone;

    @NotNull
    @Column(unique = true, name = "ds_email")
    private String email;

    @NotNull
    @Column(name = "ds_senha")
    private String senha;

    @OneToMany
    @JoinColumn(name = "cod_cliente")
    private List<Endereco> enderecos;

    @OneToMany
    @JoinColumn(name = "cod_cliente")
    private List<Pedido> pedidos;

    @NotNull
    @Column(name = "ds_sexo")
    private String sexo;
}