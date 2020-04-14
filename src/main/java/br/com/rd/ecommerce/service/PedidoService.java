package br.com.rd.ecommerce.service;

import br.com.rd.ecommerce.model.dto.ItemPedidoDTO;
import br.com.rd.ecommerce.model.dto.PedidoDTO;
import br.com.rd.ecommerce.model.entity.ItemPedido;
import br.com.rd.ecommerce.model.entity.Pedido;
import br.com.rd.ecommerce.model.entity.Status;
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
    private PedidoRepository repository;
    @Autowired
    private ProdutoService produtoService;

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
            for (ItemPedidoDTO itemDTO : pedidoDTO.getItensPedido()
            ) {
                ItemPedido itemPedido = new ItemPedido();
                itemPedido.setQuantidade(itemDTO.getQuantidade());
                itemPedido.setCodProduto(itemDTO.getCodProduto());

                itensPedido.add(itemPedido);
            }
            produtoService.atualizarEstoque(itensPedido);
            pedido.setItens(itensPedido);

            return ResponseEntity.ok().body(repository.save(pedido));
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
}
