package br.com.facol.dizimo.view;

import br.com.facol.dizimo.model.entities.Endereco;
import br.com.facol.dizimo.model.entities.Membro;
import br.com.facol.dizimo.model.enums.Genero;
import br.com.facol.dizimo.model.service.EnderecoService;
import br.com.facol.dizimo.model.service.MembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

@Component
public class MembroView {
    private static Scanner scanner = new Scanner(System.in);
    @Autowired
    private EnderecoView enderecoView;
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private MembroService membroService;

    public void cadastrarMembro() {
        Endereco endereco1 = enderecoView.cadastrarEndereco();

        System.out.println("Digite seu nome completo: ");
        String nome = scanner.nextLine();

        System.out.println("Digite seu CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Digite a data de nascimento (no formato YYYY-MM-DD): ");
        String dataNascimentoStr = scanner.nextLine();
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr);

        System.out.print("Digite o sexo (M/F): ");
        char sexoChar = scanner.nextLine().charAt(0);
        Genero sexo = Genero.fromChar(sexoChar);

        System.out.print("Digite o email do membro: ");
        String email = scanner.nextLine();

        System.out.print("Digite a senha do membro: ");
        Integer senha = scanner.nextInt();

        Optional<Endereco> enderecoEncontrado = enderecoService.listarEnderecoPorID(endereco1.getId());

        Membro membro = new Membro(nome, cpf, dataNascimento, sexo, senha, email);
        membro.setEndereco(enderecoEncontrado.get());

        membroService.salvarMembro(membro);

    }

    public void listarMembro() {
        System.out.println("Digite seu CPF: ");
        String cpf = scanner.nextLine();

        System.out.println("Digite sua senha: ");
        Integer senha = scanner.nextInt();

        Optional<Membro> membro = membroService.listarMembros(cpf,senha);

        System.out.println(membro.toString());
    }

    public void excluirMembro() throws Exception {
        System.out.println("Digite seu CPF: ");
        String cpf = scanner.nextLine();

        System.out.println("Digite sua senha: ");
        Integer senha = scanner.nextInt();

        membroService.excluirMembro(cpf,senha);
    }
}
