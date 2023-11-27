package br.com.facol.dizimo.view;

import br.com.facol.dizimo.model.entities.Endereco;
import br.com.facol.dizimo.model.entities.Igreja;
import br.com.facol.dizimo.model.service.EnderecoService;
import br.com.facol.dizimo.model.service.IgrejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

@Component
public class IgrejaView {
    private static Scanner scanner = new Scanner(System.in);
    @Autowired
    private EnderecoView enderecoView;
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private IgrejaService igrejaService;

    public void cadastrarIgreja() {
        Endereco endereco1 = enderecoView.cadastrarEndereco();

        System.out.println("Digite o nome da igreja: ");
        String nomeDaIgreja = scanner.nextLine();
        System.out.println("Digite o CNPJ da igreja: ");
        String cnpj = scanner.nextLine();
        System.out.println("Digite o email da igreja: ");
        String email = scanner.nextLine();
        System.out.println("Digite a senha da igreja: ");
        Integer senha = scanner.nextInt();
        System.out.println("Digite a data de fundação da igreja: ");
        String dataDeFundacaoStr = scanner.nextLine();
        LocalDate data = LocalDate.parse(dataDeFundacaoStr);
        System.out.println("Digite o ID do endereço da igreja: ");

        Optional<Endereco> enderecoEncontrado = enderecoService.listarEnderecoPorID(endereco1.getId());

        Igreja igreja = new Igreja(nomeDaIgreja, cnpj, senha, data, email);
        igreja.setEndereco(enderecoEncontrado.get());

        igrejaService.salvarIgreja(igreja);
    }

    public void listarIgreja() {
        System.out.println("Digite o CNPJ da igreja: ");
        String cnpj = scanner.nextLine();
        System.out.println("Digite a senha da igreja: ");
        Integer senha = scanner.nextInt();

        igrejaService.listarIgreja(cnpj, senha);
    }

    public void excluirIgreja() throws Exception {
        System.out.println("Digite o CNPJ da igreja: ");
        String cnpj = scanner.nextLine();
        System.out.println("Digite a senha da igreja: ");
        Integer senha = scanner.nextInt();

        igrejaService.excluirIgreja(cnpj, senha);
    }

}
