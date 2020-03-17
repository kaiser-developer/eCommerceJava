package br.com.rd.ecommerce.model.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_categoria")
    private Long codigo;

    @Column(unique = true, name = "ds_descricao")
    private String descricao;
}