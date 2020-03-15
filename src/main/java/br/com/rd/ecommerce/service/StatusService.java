package br.com.rd.ecommerce.service;

import br.com.rd.ecommerce.model.dto.StatusDTO;
import br.com.rd.ecommerce.model.entity.Status;
import br.com.rd.ecommerce.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("StatusService")
public class StatusService {

    @Autowired
    private StatusRepository repository;

    public ResponseEntity cadastrarStatus(StatusDTO statusDTO){
        if(statusDTO == null || statusDTO.getDescricao() == null){
            return ResponseEntity.ok().body(null);
        }
        Status status = new Status();
        status.setDescricao(statusDTO.getDescricao());
        return ResponseEntity.ok().body(repository.save(status));
    }
}
