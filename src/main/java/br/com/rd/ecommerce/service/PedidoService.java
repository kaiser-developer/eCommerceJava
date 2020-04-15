package br.com.rd.ecommerce.service;

import br.com.rd.ecommerce.controller.EmailController;
import br.com.rd.ecommerce.model.dto.ItemPedidoDTO;
import br.com.rd.ecommerce.model.dto.PedidoDTO;
import br.com.rd.ecommerce.model.dto.PedidoEmailDTO;
import br.com.rd.ecommerce.model.entity.*;
import br.com.rd.ecommerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("PedidoService")
public class PedidoService {

    @Autowired
    private ConversaoDTO conversaoDTO;
    @Autowired
    private PedidoRepository repository;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private EmailController emailController;
    @Autowired
    private EnderecoService enderecoService;


    public ResponseEntity cadastrarPedido(PedidoDTO pedidoDTO){
        try {
            if (pedidoDTO == null) {
                return ResponseEntity.ok().body(0);
            } else if (pedidoDTO.getDsFormaPagto() == null || pedidoDTO.getVlFrete().floatValue() < 0 ||
                    pedidoDTO.getCodEndereco() == null || pedidoDTO.getItensPedido().size() == 0) {
                return ResponseEntity.ok().body(1);
            }

            Pedido pedido = conversaoDTO.criarPedido(pedidoDTO);
            produtoService.atualizarEstoque(pedido.getItens());

            pedido = repository.save(pedido);

            List<Produto> produtos = produtoService.produtosPedido(pedido.getCodPedido());
            Cliente cliente = clienteService.retonarCliente(pedido.getCodCliente());
            Endereco endereco = enderecoService.retornarEndereco(pedido.getCodEndereco());

            emailController.enviarEmailPedidoRealizado(conversaoDTO.criarPedidoEmailDTO(pedido, produtos, cliente, endereco));

            return ResponseEntity.ok().body(pedido);
        }catch (Exception e){
            String erro = "Erro ao cadastrar o pedido";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity buscarPedidos(Long codCliente){
        return ResponseEntity.ok().body(repository.findByCodClienteOrderByDtPedidoDesc(codCliente));
    }

    public ResponseEntity cancelarPedido(Long codPedido){
        return repository.findById(codPedido).map(pedido -> {
            Status cancelado = new Status();
            cancelado.setCod_status(3l);
            cancelado.setDescricao("Compra cancelada");
            pedido.setStatus(cancelado);
            return ResponseEntity.ok().body(repository.save(pedido));
        }).orElse(ResponseEntity.status(404).build());
    }

    public Pedido pedido(Long codPedido){
        return repository.findById(codPedido).orElse(null);
    }

//    public ResponseEntity atualizarPedidoAprovado(Long codPedido, Long codRequisicao){
//        return repository.findById(codPedido).map(pedido -> {
//
//        }).orElse(ResponseEntity.status(404).body("Pedido não encontrado"));
//    }
//
//    public ResponseEntity atualizarPedidoTransito(Long codPedido){
//        return repository.findById(codPedido).map(pedido -> {
//
//        }).orElse(ResponseEntity.status(404).body("Pedido não encontrado"));
//    }
//
//    public ResponseEntity atualizarPedidoEntregue(Long codPedido, Long codRequisicao){
//        return repository.findById(codPedido).map(pedido -> {
//
//        }).orElse(ResponseEntity.status(404).body("Pedido não encontrado"));
//    }
}