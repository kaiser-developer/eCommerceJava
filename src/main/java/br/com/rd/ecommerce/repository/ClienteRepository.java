package br.com.rd.ecommerce.repository;

import br.com.rd.ecommerce.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByEmailAndSenha(String email, String senha);
    Cliente findByEmail(String email);
    Cliente findByCpf(String cpf);
}
