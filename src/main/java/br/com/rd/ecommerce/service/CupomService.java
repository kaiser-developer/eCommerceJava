package br.com.rd.ecommerce.service;

import br.com.rd.ecommerce.model.dto.CupomDTO;
import br.com.rd.ecommerce.model.entity.Cupom;
import br.com.rd.ecommerce.repository.CupomRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("CupomService")
public class CupomService {

    @Autowired
    private CupomRepositoy repositoy;

    public ResponseEntity cadastrarCupom(CupomDTO cupomDTO){
        try {
            Cupom cupom = new Cupom();
            cupom.setAtivo(true);
            cupom.setDesconto(cupomDTO.getDesconto());
            cupom.setNome(cupomDTO.getNome());

            return ResponseEntity.ok().body(repositoy.save(cupom));
        } catch (Exception e) {
            String erro = "Erro ao cadastrar cupom no banco";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity buscarCupom(CupomDTO cupomDTO){
        try {
            return ResponseEntity.ok().body(repositoy.findByNome(cupomDTO.getNome()));
        } catch (Exception e){
            String erro = "Erro ao buscar cupom";
            return ResponseEntity.badRequest().body(erro);
        }
    }
}
