package prova.compasso.com.br.prova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prova.compasso.com.br.prova.model.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
