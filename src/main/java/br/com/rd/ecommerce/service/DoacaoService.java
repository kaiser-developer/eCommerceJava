package br.com.rd.ecommerce.service;

import br.com.rd.ecommerce.model.dto.DoacaoDTO;
import br.com.rd.ecommerce.model.dto.ItemDoadoDTO;
import br.com.rd.ecommerce.model.entity.Doacao;
import br.com.rd.ecommerce.model.entity.ItemDoado;
import br.com.rd.ecommerce.model.entity.Status;
import br.com.rd.ecommerce.repository.DoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DoacaoService {

    @Autowired
    private DoacaoRepository repository;

    public ResponseEntity cadastrarDoacao(DoacaoDTO doacaoDTO){
        try {
            if(doacaoDTO == null) {
                return ResponseEntity.ok().body(0);
            }
            else if(doacaoDTO.getDsFormaPagto() == null || doacaoDTO.getLocalDoacao() == null ||
                    doacaoDTO.getItensDoados().size() == 0) {
                return ResponseEntity.ok().body(1);
            }

            Doacao doacao = new Doacao();
            Status status = new Status();
            List<ItemDoado> itensDoados = new ArrayList<>();

            status.setCod_status(1L);
            doacao.setDsFormaPagto(doacaoDTO.getDsFormaPagto());
            doacao.setDtDoacao(new Date());
            doacao.setLocalDoacao(doacaoDTO.getLocalDoacao());
            doacao.setVlDoacao(doacaoDTO.getVlDoacao());
            doacao.setCodCliente(doacaoDTO.getCodCliente());
            doacao.setStatus(doacaoDTO.getStatus());
            for(ItemDoadoDTO itemDTO : doacaoDTO.getItensDoados()) {
                ItemDoado itemDoado = new ItemDoado();
                itemDoado.setQuantidadeDoada(itemDTO.getQuantidadeDoada());
                itemDoado.setCodProdutoDoacao(itemDTO.getCodItemDoado());

                itensDoados.add(itemDoado);
            }
            doacao.setItensDoados(itensDoados);

            return ResponseEntity.ok().body(repository.save(doacao));
            } catch (Exception e) {
            String erro = "Erro ao cadastrar doação.";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity buscarDoacao(Long codCliente){
        return ResponseEntity.ok().body(repository.findByCodCliente(codCliente));
    }

   public ResponseEntity cancelarDoacao(Long codDoacao){
        return repository.findById(codDoacao).map(doacao -> {
            Status cancelado = new Status();
            cancelado.setCod_status(3l);
            cancelado.setDescricao("Doação cancelada");
            doacao.setStatus(cancelado);
            return ResponseEntity.ok().body(repository.save(doacao));
        }).orElse(ResponseEntity.status(404).build());
   }



}



