package br.com.rd.ecommerce.service;

import br.com.rd.ecommerce.model.dto.StatusDTO;
import br.com.rd.ecommerce.model.dto.StatusFaleConoscoDTO;
import br.com.rd.ecommerce.model.entity.Status;
import br.com.rd.ecommerce.model.entity.StatusFaleConosco;
import br.com.rd.ecommerce.repository.StatusFaleConoscoRepository;
import br.com.rd.ecommerce.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("StatusFaleConoscoService")
public class StatusFaleConoscoService {

    @Autowired
    private StatusFaleConoscoRepository repository;

    public ResponseEntity cadastrarStatusFL(StatusFaleConoscoDTO statusFaleConoscoDTO){
        try{
            if(statusFaleConoscoDTO == null || statusFaleConoscoDTO.getStatus() == null){
                return ResponseEntity.ok().body(null);
            }
            StatusFaleConosco statusFaleConosco = new StatusFaleConosco();
            statusFaleConosco.setStatus(statusFaleConoscoDTO.getStatus());
            return ResponseEntity.ok().body(repository.save(statusFaleConosco));
        }catch (Exception e){
            String erro = "Status de pedido inexistente";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity buscarStatusFL(){
        try {
            return ResponseEntity.ok().body(repository.findAll());
        }
        catch (Exception e){
            String erro = "Busca inexistente";
            return ResponseEntity.badRequest().body(erro);
        }
    }
}