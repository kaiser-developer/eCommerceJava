package br.com.rd.ecommerce.service;

import br.com.rd.ecommerce.model.dto.PedidoEmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailContentBuilder {
    private TemplateEngine templateEngine;

    @Autowired
    public EmailContentBuilder(TemplateEngine templateEngine){
        this.templateEngine = templateEngine;
    }

    public String build(PedidoEmailDTO pedidoEmailDTO){
        Context context = new Context();
        context.setVariable("pedido", pedidoEmailDTO);
        return templateEngine.process("exemplo", context);
    }
}
