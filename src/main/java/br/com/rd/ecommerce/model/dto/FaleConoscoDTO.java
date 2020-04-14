package br.com.rd.ecommerce.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaleConoscoDTO {
    private Long codFaleConosco;
    private String nomeCompleto;
    private String telefone;
    private String email;
    private String mensagem;
    private String assuntoMensagem;
    private Long codCliente;
    private StatusFaleConoscoDTO statusFL;
}
