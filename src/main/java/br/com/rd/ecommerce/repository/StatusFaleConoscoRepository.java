package br.com.rd.ecommerce.repository;

import br.com.rd.ecommerce.model.entity.StatusFaleConosco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusFaleConoscoRepository extends JpaRepository <StatusFaleConosco, Long> {

}
