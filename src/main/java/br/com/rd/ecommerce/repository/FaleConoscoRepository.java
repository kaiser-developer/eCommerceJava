package br.com.rd.ecommerce.repository;

import br.com.rd.ecommerce.model.entity.FaleConosco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaleConoscoRepository extends JpaRepository<FaleConosco, Long> {
    FaleConosco findByCodFaleConosco (Long CodFaleConosco);
}