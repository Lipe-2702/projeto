package br.com.facol.dizimo.model.repository;

import br.com.facol.dizimo.model.entities.Membro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembroRepository extends JpaRepository<Membro, Long> {
    Optional<Membro> findByCpfAndSenhaAndAtivoIsTrue(String cpf,Integer senha);
    Membro getReferenceByCpfAndSenhaAndAtivoIsTrue(String cpf, Integer senha);
}