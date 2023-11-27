package br.com.facol.dizimo.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_contas")
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(unique = true)
    private Integer numeroDaConta;
    private Integer senha;
    private BigDecimal saldo;
    private LocalDate dataDeAberturaDaConta;
    private Boolean ativo;

    protected Conta(Integer numeroDaConta, Integer senha) {
        this.numeroDaConta = numeroDaConta;
        this.senha = senha;
        this.saldo = BigDecimal.ZERO;
        this.dataDeAberturaDaConta = LocalDate.now();
        this.setAtivo(true);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("  ID: ").append(id).append("\n");
        sb.append("  Número da conta: ").append(numeroDaConta).append("\n");
        sb.append("  Senha: ").append(senha).append("\n");
        sb.append("  Saldo: ").append(saldo).append("\n");
        sb.append("  Data de abertura da conta: ").append(dataDeAberturaDaConta).append("\n");
        sb.append("}");
        return sb.toString();
    }

}
