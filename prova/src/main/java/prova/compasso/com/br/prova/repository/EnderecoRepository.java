package prova.compasso.com.br.prova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prova.compasso.com.br.prova.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
