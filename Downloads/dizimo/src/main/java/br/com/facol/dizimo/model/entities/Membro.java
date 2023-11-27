package br.com.facol.dizimo.model.entities;

import br.com.facol.dizimo.model.enums.Genero;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_membros")
@Getter
@Setter
@NoArgsConstructor
public class Membro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @CPF
    @Column(name = "cpf", unique = true, nullable = false, length = 14)
    private String cpf;
    private String nome;
    private Integer idade;
    private LocalDate dataDeNascimento;
    private Genero sexo;
    private String email;
    private Integer senha;
    private Boolean ativo;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Endereco endereco;
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "membro")
    private ContaMembro contaMembro;


    public Membro(String nome, String cpf, LocalDate dataDeNascimento, Genero sexo, Integer senha, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.sexo = sexo;
        this.senha = senha;
        this.email = email;
        this.ativo = true;
    }

    public Membro(String nome, String email, Integer senha, Endereco enderecoAtualizado) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = enderecoAtualizado;
    }

    public BigDecimal valorDaDoacao(){
        return this.getContaMembro().getSaldo();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Membro {\n");
        sb.append("  ID: ").append(id).append("\n");
        sb.append("  CPF: ").append(cpf).append("'\n");
        sb.append("  nome: ").append(nome).append("'\n");
        sb.append("  Idade: ").append(idade).append("\n");
        sb.append("  data de nascimento: ").append(dataDeNascimento).append("\n");
        sb.append("  Sexo: ").append(sexo.getDescricao()).append("\n");
        sb.append("  email: ").append(email).append("'\n");
        sb.append("  Senha: ").append(senha).append("\n");
        sb.append("  Endereço: ").append(endereco.toString()).append("\n");
        sb.append("}");
        return sb.toString();
    }

}
