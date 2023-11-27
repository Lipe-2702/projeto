package br.com.facol.dizimo.model.entities;

import br.com.facol.dizimo.model.enums.Estado;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_enderecos")
@Getter
@Setter
@NoArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rua;
    private Integer numero;
    private String bairro;
    private String cidade;
    private Estado estado;

    public Endereco(String rua, Integer numero, String bairro, String cidade, Estado estado) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Endereço {\n");
        sb.append("  ID: ").append(id).append("\n");
        sb.append("  Rua: ").append(rua).append("'\n");
        sb.append("  Numero: ").append(numero).append("\n");
        sb.append("  Bairro: ").append(bairro).append("'\n");
        sb.append("  Cidade: ").append(cidade).append("'\n");
        sb.append("  Estado: ").append(estado.getFullName()).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
