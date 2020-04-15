package br.com.rd.ecommerce.service;

import br.com.rd.ecommerce.model.dto.ItemPedidoDTO;
import br.com.rd.ecommerce.model.dto.PedidoDTO;
import br.com.rd.ecommerce.model.dto.PedidoEmailDTO;
import br.com.rd.ecommerce.model.dto.ProdutoEmailDTO;
import br.com.rd.ecommerce.model.entity.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("CriacaoDTO")
public class ConversaoDTO {

    public PedidoEmailDTO criarPedidoEmailDTO(Pedido pedido, List<Produto> produtos, Cliente cliente, Endereco endereco){
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

    public Pedido criarPedido(PedidoDTO pedidoDTO){

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
        pedido.setItens(itensPedido);

        return pedido;
    }
}
