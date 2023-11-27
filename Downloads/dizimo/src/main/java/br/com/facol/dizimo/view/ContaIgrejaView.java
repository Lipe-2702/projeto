package br.com.facol.dizimo.view;

import br.com.facol.dizimo.model.entities.ContaIgreja;
import br.com.facol.dizimo.model.service.ContaIgrejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ContaIgrejaView {
    @Autowired
    ContaIgrejaService igrejaService;
    private static Scanner scanner = new Scanner(System.in);

    public void cadastrarContaIgreja() {
        System.out.println("Digite o seu CNPJ: ");
        String cnpj = scanner.nextLine();
        System.out.println("Digite a sua senha: ");
        Integer senhaIgreja = scanner.nextInt();

        System.out.println("Crie um numero para sua conta: ");
        Integer numDaConta = scanner.nextInt();
        System.out.println("Crie uma senha para sua conta: ");
        Integer senha = scanner.nextInt();

        ContaIgreja contaIgreja = new ContaIgreja(numDaConta,senha);

        igrejaService.cadastrarContaIgreja(cnpj,senhaIgreja,contaIgreja);
    }

}
