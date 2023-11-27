package br.com.facol.dizimo.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ContaMembro extends Conta {

    @OneToOne
    private Membro membro;

    public ContaMembro(Integer numDaConta, Integer senha) {
        super(numDaConta,senha);
    }

    @Override
    public String toString() {
        return "\nTitular: " + this.getMembro().getNome() + super.toString();
    }
}
