package br.com.rd.ecommerce.service;

import br.com.rd.ecommerce.model.dto.FaleConoscoDTO;
import br.com.rd.ecommerce.model.dto.StatusFaleConoscoDTO;
import br.com.rd.ecommerce.model.entity.FaleConosco;
import br.com.rd.ecommerce.model.entity.Status;
import br.com.rd.ecommerce.model.entity.StatusFaleConosco;
import br.com.rd.ecommerce.repository.FaleConoscoRepository;
import br.com.rd.ecommerce.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("FaleConoscoService")
public class FaleConoscoService {

    @Autowired
    private FaleConoscoRepository repository;

    public ResponseEntity cadastrarMensagem(FaleConoscoDTO faleConoscoDTO){
        try{
            FaleConosco mensagem = new FaleConosco();
            StatusFaleConosco status = new StatusFaleConosco();
            mensagem.setAssuntoMensagem(faleConoscoDTO.getAssuntoMensagem());
            mensagem.setCodCliente(faleConoscoDTO.getCodCliente());
            mensagem.setEmail(faleConoscoDTO.getEmail());
            mensagem.setMensagem(faleConoscoDTO.getMensagem());
            mensagem.setNomeCompleto(faleConoscoDTO.getNomeCompleto());
            mensagem.setTelefone(faleConoscoDTO.getTelefone());
            status.setCodStatus(faleConoscoDTO.getStatusFL().getCod_status());

            mensagem.setStatusFL(status);

            return ResponseEntity.ok().body(repository.save(mensagem));
        }catch (Exception e){
            String erro = "Mensagem não pode ser enviada";
            return ResponseEntity.badRequest().body(erro);
        }
    }
}