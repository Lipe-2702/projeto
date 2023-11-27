package br.com.facol.dizimo.model.repository;

import br.com.facol.dizimo.model.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}