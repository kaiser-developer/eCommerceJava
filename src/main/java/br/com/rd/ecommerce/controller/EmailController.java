package br.com.rd.ecommerce.controller;

import br.com.rd.ecommerce.model.dto.PedidoEmailDTO;
import br.com.rd.ecommerce.model.dto.ProdutoEmailDTO;
import br.com.rd.ecommerce.model.entity.Cliente;
import br.com.rd.ecommerce.model.entity.Endereco;
import br.com.rd.ecommerce.model.entity.Pedido;
import br.com.rd.ecommerce.model.entity.Produto;
import br.com.rd.ecommerce.service.ClienteService;
import br.com.rd.ecommerce.service.EmailContentBuilder;
import br.com.rd.ecommerce.service.EnderecoService;
import br.com.rd.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class EmailController {

    @Autowired private JavaMailSender emailSender;
    @Autowired private EmailContentBuilder emailContentBuilder;

    @Async
    public void emailRecuperacaoSenha(Cliente cliente){

                SimpleMailMessage message = new SimpleMailMessage();
                message.setText("Olá, " + cliente.getNome() + "\n\n" +
                        "você solicitou a recuperação da sua senha recentemente\n\n" +
                        "Use o seguinte código de recupeção de senha: " + cliente.getCodRecuperarSenha() +
                        "\n\n" +
                        "Você tem 10 minutos para utilizar o código" +
                        "\n\nCaso não tenha feito a solicitação desconsidere essa mensagem!");
                message.setTo(cliente.getEmail());
                message.setSubject("Confirmação para redefinição de senha do Wig's House");
                message.setFrom("rd.projetoperuca@gmail.com");

                try {
                    emailSender.send(message);
                } catch (MailException e) {
                    e.printStackTrace();
                }
    }

    @Async
    public void enviarEmailPedidoRealizado(PedidoEmailDTO pedidoEmailDTO){

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("rd.projetoperuca@gmail.com");
            messageHelper.setTo(pedidoEmailDTO.getEmail());
            messageHelper.setSubject("Atualizando pedido");
            String content = emailContentBuilder.build(pedidoEmailDTO);
            messageHelper.setText(content, true);

        };
        try {
            emailSender.send(messagePreparator);
        } catch (MailException e) {
            e.toString();
        }
    }

    public String enviarAtualizacao(List<Produto> produtos){

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("rd.projetoperuca@gmail.com");
            messageHelper.setTo("asa.cesar@gmail.com");
            messageHelper.setSubject("Atualizando pedido");
            String content = emailContentBuilder.build(null);
            messageHelper.setText(content, true);

        };
        try {
            emailSender.send(messagePreparator);
            return "funciona";
        } catch (MailException e) {
            return e.toString();
        }
    }
}
