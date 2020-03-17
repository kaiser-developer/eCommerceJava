package br.com.rd.ecommerce.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Long codCliente;

    private String nome;

    private String cpf;

    private String email;

    private String senha;

    private String telefone;

    private String sexo;
}
