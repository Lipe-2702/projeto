package br.com.facol.dizimo.model.repository;

import br.com.facol.dizimo.model.entities.Igreja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IgrejaRepository extends JpaRepository<Igreja, Long> {
    Igreja findByCnpjAndSenhaAndAtivoIsTrue(String cnpj, Integer senha);

    Igreja getReferenceByCnpjAndSenhaAndAtivoIsTrue(String cnpj, Integer senha);
}
