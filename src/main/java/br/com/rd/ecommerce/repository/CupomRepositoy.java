package br.com.rd.ecommerce.repository;

import br.com.rd.ecommerce.model.entity.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CupomRepositoy extends JpaRepository<Cupom, Long> {
    Cupom findByNome(String nome);
}
