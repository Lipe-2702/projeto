package br.com.facol.dizimo.model.repository;

import br.com.facol.dizimo.model.entities.ContaIgreja;
import br.com.facol.dizimo.model.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaIgrejaRepository extends JpaRepository<ContaIgreja, Long> {

    Optional<ContaIgreja> findByNumeroDaContaAndSenhaAndAtivoIsTrue(Integer numDaConta,Integer senha);
}