package br.com.facol.dizimo.model.repository;

import br.com.facol.dizimo.model.entities.ContaMembro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaMembroRepository extends JpaRepository<ContaMembro, Long> {
    Optional<ContaMembro> findByNumeroDaContaAndSenhaAndAtivoIsTrue(Integer numDaConta,Integer senha);
}