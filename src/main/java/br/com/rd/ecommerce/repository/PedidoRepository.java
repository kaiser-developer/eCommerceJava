package br.com.rd.ecommerce.repository;

import br.com.rd.ecommerce.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByCodCliente(Long codCliente);
}
