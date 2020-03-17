package br.com.rd.ecommerce.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {

    private String destinatario;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;
    private Long codCliente;
}
