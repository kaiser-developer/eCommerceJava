package br.com.rd.ecommerce.service;

import br.com.rd.ecommerce.model.dto.CupomDTO;
import br.com.rd.ecommerce.model.entity.Cupom;
import br.com.rd.ecommerce.repository.CupomRepositoy;
import org.hibernate.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service("CupomService")
public class CupomService {

    @Autowired
    private CupomRepositoy repository;
    @PersistenceContext
    private EntityManager em;

    public ResponseEntity cadastrarCupom(CupomDTO cupomDTO){
        try {
            Cupom cupom = new Cupom();
            cupom.setAtivo(cupomDTO.getAtivo());
            cupom.setDesconto(cupomDTO.getDesconto());
            cupom.setNome(cupomDTO.getNome());
            return ResponseEntity.ok().body(repository.save(cupom));
        } catch (Exception e) {
            String erro = "Erro ao cadastrar cupom no banco";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity buscarCupom(CupomDTO cupomDTO){
        try {
            return ResponseEntity.ok().body(repository.findByNome(cupomDTO.getNome()));
        } catch (Exception e){
            String erro = "Erro ao buscar cupom";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity buscarTodosCupons(){
        try {
            return ResponseEntity.ok().body(repository.findAll());
        } catch (Exception e){
            String erro = "Erro ao buscar cupons";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity buscarCuponsCliente(Long codCliente){
        try {
            List<Cupom> cupons = null;
            String sql =
                    new StringBuffer()
                    .append("SELECT c.* FROM TB_CUPOM c")
                    .append(" LEFT JOIN")
                    .append(" (SELECT pedido.cod_cupom FROM TB_PEDIDO pedido")
                    .append(" WHERE pedido.cod_cupom IS NOT NULL AND pedido.cod_cliente = "+ codCliente +") aux ")
                    .append(" ON c.cod_cupom = aux.cod_cupom ")
                    .append(" WHERE aux.cod_cupom IS NULL AND c.bl_ativo = true").toString();

            Query query = em.createNativeQuery(sql, Cupom.class);
            cupons = query.getResultList();
            return ResponseEntity.ok().body(cupons);
        } catch (QueryException e){
            String erro = "Erro ao buscar cupons";
            return ResponseEntity.badRequest().body(erro + e.getMessage());
        }
    }
}
