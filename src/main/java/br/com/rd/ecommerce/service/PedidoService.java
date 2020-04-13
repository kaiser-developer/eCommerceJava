package br.com.rd.ecommerce.service;

import br.com.rd.ecommerce.controller.EmailController;
import br.com.rd.ecommerce.model.dto.ItemPedidoDTO;
import br.com.rd.ecommerce.model.dto.PedidoDTO;
import br.com.rd.ecommerce.model.dto.PedidoEmailDTO;
import br.com.rd.ecommerce.model.dto.ProdutoEmailDTO;
import br.com.rd.ecommerce.model.entity.*;
import br.com.rd.ecommerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("PedidoService")
public class PedidoService {

    @Autowired
    private PedidoRepository repository;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private EmailController emailController;
    @Autowired
    private ClienteService clienteService;
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
            Pedido pedido = new Pedido();
            Status status = new Status();
            List<ItemPedido> itensPedido = new ArrayList<>();
            status.setCod_status(1L);
            pedido.setDsFormaPagto(pedidoDTO.getDsFormaPagto());
            pedido.setDtPedido(new Date());
            pedido.setCodEndereco(pedidoDTO.getCodEndereco());
            pedido.setVlFrete(pedidoDTO.getVlFrete());
            pedido.setCodCliente(pedidoDTO.getCodCliente());
            pedido.setVlPedido(pedidoDTO.getVlPedido());
            pedido.setCupom(pedidoDTO.getCupom());
            pedido.setStatus(status);
            for (ItemPedidoDTO itemDTO : pedidoDTO.getItensPedido()) {
                ItemPedido itemPedido = new ItemPedido();
                itemPedido.setQuantidade(itemDTO.getQuantidade());
                itemPedido.setCodProduto(itemDTO.getCodProduto());

                itensPedido.add(itemPedido);
            }
            produtoService.atualizarEstoque(itensPedido);
            pedido.setItens(itensPedido);

            pedido = repository.save(pedido);
            emailController.enviarEmailPedidoRealizado(retornarPedidoEmailDTO(pedido));

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

    public PedidoEmailDTO retornarPedidoEmailDTO(Pedido pedido){
        List<Produto> produtos = produtoService.produtosPedido(pedido.getCodPedido());
        Cliente cliente = clienteService.retonarCliente(pedido.getCodCliente());
        Endereco endereco = enderecoService.retornarEndereco(pedido.getCodEndereco());

        PedidoEmailDTO pedidoEmailDTO = new PedidoEmailDTO();
        BigDecimal subtotal = BigDecimal.ZERO;
        BigDecimal desconto = BigDecimal.ZERO;
        List<ProdutoEmailDTO> produtoEmailDTOS = new ArrayList<>();

        pedidoEmailDTO.setCodPedido(pedido.getCodPedido());
        pedidoEmailDTO.setStatus(pedido.getStatus());
        pedidoEmailDTO.setDataPedido(pedido.getDtPedido());
        pedidoEmailDTO.setValorFrete(pedido.getVlFrete());
        pedidoEmailDTO.setValorTotal(pedido.getVlPedido());
        pedidoEmailDTO.setEntrega(pedido.getDataEntrega());
        pedidoEmailDTO.setEndereco(endereco);
        pedidoEmailDTO.setNomeCliente(cliente.getNome());
        pedidoEmailDTO.setEmail(cliente.getEmail());

        for(int index = 0; index < pedido.getItens().size(); index++){
            ProdutoEmailDTO produtoEmailDTO = new ProdutoEmailDTO();

            produtoEmailDTO.setNome(produtos.get(index).getDescricao());
            produtoEmailDTO.setPreco(produtos.get(index).getValorProduto());
            produtoEmailDTO.setImagem(produtos.get(index).getImagens().get(0).getLocal());
            produtoEmailDTO.setQuantidade(pedido.getItens().get(index).getQuantidade());

            produtoEmailDTOS.add(produtoEmailDTO);
            subtotal = subtotal.add(produtoEmailDTO.getPreco().multiply(new BigDecimal(produtoEmailDTO.getQuantidade().intValue())));
        }

        if(pedido.getCupom() != null){
            desconto = desconto.subtract(subtotal.multiply(new BigDecimal(pedido.getCupom().getDesconto() / 100)));
        }

        pedidoEmailDTO.setDesconto(desconto);

        pedidoEmailDTO.setProdutos(produtoEmailDTOS);
        pedidoEmailDTO.setSubTotal(subtotal);

        return pedidoEmailDTO;
    }
}
