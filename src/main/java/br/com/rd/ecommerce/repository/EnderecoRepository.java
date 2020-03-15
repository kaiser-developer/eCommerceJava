package br.com.rd.ecommerce.repository;

import br.com.rd.ecommerce.model.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    List<Endereco> findByCodCliente(Long codCliente);
}
