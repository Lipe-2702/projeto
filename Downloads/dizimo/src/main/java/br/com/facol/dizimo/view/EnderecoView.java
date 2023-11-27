package br.com.facol.dizimo.view;

import br.com.facol.dizimo.model.entities.Endereco;
import br.com.facol.dizimo.model.enums.Estado;
import br.com.facol.dizimo.model.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class EnderecoView {
    private static Scanner scanner = new Scanner(System.in);
    @Autowired
    private EnderecoService enderecoService;

    public Endereco cadastrarEndereco() {
        System.out.println("Digite o nome da rua: ");
        String rua = scanner.nextLine();

        System.out.println("Digite o numero da sua residencia: ");
        Integer numero = scanner.nextInt();

        System.out.println("Digite o nome do seu bairro: ");
        String bairro = scanner.nextLine();
        bairro = scanner.nextLine();

        System.out.println("Digite o nome da sua cidade: ");
        String cidade = scanner.nextLine();

        System.out.println("Digite a sigla do seu estado (ex: SP): ");
        String estado = scanner.nextLine();
        Estado estado1 = Estado.acronym(estado);

        var endereco = new Endereco(rua,numero,bairro,cidade,estado1);

        Endereco enderecoCadastrado = enderecoService.salvarEndereco(endereco);

        return enderecoCadastrado;
    }
}
