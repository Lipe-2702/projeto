package br.com.facol.dizimo.model.validation;

import br.com.facol.dizimo.model.entities.Membro;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class ValidarDataDeNascimento implements IValidar<Membro> {

    @Override
    public void validar(Membro membro) {
        var dataAtual = LocalDate.now();

        if (membro.getDataDeNascimento().isAfter(dataAtual)) {
            throw new IllegalArgumentException("A data de nascimento não pode ser maior que a data atual.");
        }
    }
}
