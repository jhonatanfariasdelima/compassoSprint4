package prova.compasso.com.br.prova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prova.compasso.com.br.prova.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
