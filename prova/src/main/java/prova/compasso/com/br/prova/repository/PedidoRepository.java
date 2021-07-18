package prova.compasso.com.br.prova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prova.compasso.com.br.prova.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
