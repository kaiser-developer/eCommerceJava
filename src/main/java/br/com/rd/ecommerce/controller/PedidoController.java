package br.com.rd.ecommerce.controller;

import br.com.rd.ecommerce.model.dto.PedidoDTO;
import br.com.rd.ecommerce.model.dto.PedidoEmailDTO;
import br.com.rd.ecommerce.model.dto.ProdutoEmailDTO;
import br.com.rd.ecommerce.model.entity.Cliente;
import br.com.rd.ecommerce.model.entity.Endereco;
import br.com.rd.ecommerce.model.entity.Pedido;
import br.com.rd.ecommerce.model.entity.Produto;
import br.com.rd.ecommerce.service.ClienteService;
import br.com.rd.ecommerce.service.EnderecoService;
import br.com.rd.ecommerce.service.PedidoService;
import br.com.rd.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RestController
public class PedidoController {

    @Autowired
    private PedidoService service;
    @Autowired
    private EmailController emailController;

    @PostMapping("cadastrar-pedido")
    public ResponseEntity cadastrarPedido(@RequestBody PedidoDTO pedidoDTO){
        return service.cadastrarPedido(pedidoDTO);
    }

    @GetMapping("buscar-pedidos/{id}")
    public ResponseEntity buscarPedidos(@PathVariable ("id") Long codCliente){
        return service.buscarPedidos(codCliente);
    }
    @PatchMapping("/cancelar-pedido/{id}")
    public ResponseEntity cancelarPedido(@PathVariable ("id") Long codPedido){
        return service.cancelarPedido(codPedido);
    }

//    @GetMapping("atualizar-pedido/{id}")
//    public ResponseEntity atualizarPedido(@PathVariable ("id") Long codPedido){
//        return ResponseEntity.ok().body(service.produtosPedido(codPedido));
//    }
}


