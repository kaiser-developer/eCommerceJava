package br.com.rd.ecommerce.repository;

import br.com.rd.ecommerce.model.entity.Cupom;
import br.com.rd.ecommerce.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByCodClienteOrderByDtPedidoDesc(Long codCliente);
    List<Pedido> findByCodClienteAndCupomIsNotNull(Long codCliente);
}