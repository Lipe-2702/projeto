package br.com.facol.dizimo.view;

import br.com.facol.dizimo.model.entities.ContaMembro;
import br.com.facol.dizimo.model.service.ContaMembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ContaMembroView {
    @Autowired
    private ContaMembroService contaMembroService;
    private static Scanner scanner = new Scanner(System.in);

    public void cadastrarContaMembro() {
        System.out.println("Digite o seu CPF: ");
        String cpf = scanner.nextLine();
        System.out.println("Digite a sua senha: ");
        Integer senhaMembro = scanner.nextInt();

        System.out.println("Crie um numero para sua conta: ");
        Integer numDaConta = scanner.nextInt();
        System.out.println("Crie uma senha para sua conta: ");
        Integer senha = scanner.nextInt();

        ContaMembro contaMembro = new ContaMembro(numDaConta,senha);

        contaMembroService.cadastrarContaMembro(cpf,senhaMembro,contaMembro);
    }

    public void listarContaMembro() {
        System.out.println("Digite o numero da sua conta: ");
        Integer numDaConta = scanner.nextInt();
        System.out.println("Digite a senha da sua conta: ");
        Integer senha = scanner.nextInt();

        contaMembroService.listarContaIgreja(numDaConta,senha);
    }

    public void excluirContaMembro() throws Exception {
        System.out.println("Digite o numero da sua conta: ");
        Integer numDaConta = scanner.nextInt();
        System.out.println("Digite a senha da sua conta: ");
        Integer senha = scanner.nextInt();

        contaMembroService.excluirContaMembro(numDaConta,senha);
    }

    public void alterarMembro() {
        System.out.println("Digite o numero da sua conta: ");
        Integer numDaConta = scanner.nextInt();
        System.out.println("Digite a senha da sua conta: ");
        Integer senha = scanner.nextInt();

        System.out.println("Digite o novo numero da sua conta: ");
        Integer novoNumDaConta = scanner.nextInt();
        System.out.println("Digite a nova senha da sua conta: ");
        Integer novaSenha = scanner.nextInt();

        ContaMembro contaMembro = new ContaMembro(novoNumDaConta,novaSenha);

        contaMembroService.alterarContaMembro(numDaConta,senha,contaMembro);
    }

}
