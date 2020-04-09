package br.com.rd.ecommerce.controller;

import br.com.rd.ecommerce.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired private JavaMailSender mailSender;

    @Async
    public void emailRecuperacaoSenha(Cliente cliente){
        new Thread(new Runnable() {
            @Override
            public void run() {
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
                    mailSender.send(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Async
    public void enviarEmailStatusPedido(){

    }
}
