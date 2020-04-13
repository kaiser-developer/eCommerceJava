package br.com.rd.ecommerce.model.entity;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table (name = "tb_status_fale_conosco")
public class StatusFaleConosco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "cod_status")
    private Long codStatus;

    @NotNull
    @Column ( name = "ds_status")
    private String status;

}