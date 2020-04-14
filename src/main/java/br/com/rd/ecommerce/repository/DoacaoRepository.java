package br.com.rd.ecommerce.repository;

import br.com.rd.ecommerce.model.entity.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoacaoRepository extends JpaRepository<Doacao, Long> {

    List<Doacao> findByCodCliente (Long codCliente);
}
