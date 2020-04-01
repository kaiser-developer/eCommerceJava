package br.com.rd.ecommerce.controller;

import br.com.rd.ecommerce.model.dto.PedidoDTO;
import br.com.rd.ecommerce.model.entity.Pedido;
import br.com.rd.ecommerce.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class PedidoController {

    @Autowired
    private PedidoService service;

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
}


