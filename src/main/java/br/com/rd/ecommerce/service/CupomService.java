package br.com.rd.ecommerce.service;

import br.com.rd.ecommerce.model.dto.CupomDTO;
import br.com.rd.ecommerce.model.entity.Cliente;
import br.com.rd.ecommerce.model.entity.Cupom;
import br.com.rd.ecommerce.model.entity.Pedido;
import br.com.rd.ecommerce.repository.CupomRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;

@Service("CupomService")
public class CupomService {

    @Autowired
    private CupomRepositoy repository;

    @Autowired
    private PedidoService pedidoService;

    @PersistenceContext
    private EntityManager em;


    public ResponseEntity cadastrarCupom(CupomDTO cupomDTO) {
        try {
            Cupom cupom = new Cupom();
            cupom.setAtivo(true);
            cupom.setDesconto(cupomDTO.getDesconto());
            cupom.setNome(cupomDTO.getNome());
            return ResponseEntity.ok().body(repository.save(cupom));
        } catch (Exception e) {
            String erro = "Erro ao cadastrar cupom no banco";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity buscarCupom(CupomDTO cupomDTO) {
        try {
            return ResponseEntity.ok().body(repository.findByNome(cupomDTO.getNome()));
        } catch (Exception e) {
            String erro = "Erro ao buscar cupom";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity deletarCupom(Long id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.ok().body("Deletado o Cupom ");
        } catch (Exception e) {
            String erro = "Não foi possivel deletar o Cumpo";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity atualizarCupom(Long id, @Valid CupomDTO cupomDTO) {
        try {
            return repository.findById(id).map(guardar -> { ;
                guardar.setAtivo(cupomDTO.getAtivo());
                Cupom atualizado = repository.save(guardar);
                return ResponseEntity.ok().body(atualizado);
            }).orElse(ResponseEntity.notFound().build());

        } catch (Exception e) {
            String erro = "Não foi possivel atualizar o Cupom!";
            return ResponseEntity.badRequest().body(erro + e.getMessage());
        }
    }

    public ResponseEntity buscarTodosCupons() {
        try {
            return ResponseEntity.ok().body(repository.findAll());
        } catch (Exception e) {
            String erro = "Erro ao buscar cupons";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity filtrarCupom(Long codCliente) {
        try {
            List<Cupom> cupons = null;

            String sql = new StringBuffer()
                    .append("SELECT c.* FROM TB_CUPOM c")
                    .append(" LEFT JOIN (SELECT pedido.cod_cupom FROM TB_PEDIDO pedido WHERE pedido.cod_cupom IS NOT NULL AND pedido.cod_cliente = " + codCliente + ")")
                    .append(" aux ON c.cod_cupom = aux.cod_cupom WHERE aux.cod_cupom IS NULL AND c.bl_ativo = true").toString();
            Query query = em.createNativeQuery(sql, Cupom.class);
            cupons = query.getResultList();
            return ResponseEntity.ok().body(cupons);
        } catch (Exception e) {
            String erro = "Erro ao buscar o cupom";
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}