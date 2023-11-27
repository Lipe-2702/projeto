package br.com.facol.dizimo.view;

import br.com.facol.dizimo.model.service.TransacaoBancariaContaMembro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class TransacaoMembroView {
    private static Scanner scanner = new Scanner(System.in);
    @Autowired
    private TransacaoBancariaContaMembro transacaoBancariaContaMembro;

    public void depositar() {
        System.out.println("Digite o numero da conta: ");
        Integer numConta = scanner.nextInt();
        System.out.println("Digite a senha: ");
        Integer senha = scanner.nextInt();

        System.out.println("Digite o valor a ser depositado: ");
        BigDecimal valor = scanner.nextBigDecimal();

        try {
            transacaoBancariaContaMembro.depositar(numConta, senha, valor);
            System.out.println("Deposito realizado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void sacar() {
        System.out.println("Digite o numero da conta: ");
        Integer numConta = scanner.nextInt();
        System.out.println("Digite a senha: ");
        Integer senha = scanner.nextInt();

        System.out.println("Digite o valor a ser sacado: ");
        BigDecimal valor = scanner.nextBigDecimal();

        try {
            transacaoBancariaContaMembro.sacar(numConta, senha, valor);
            System.out.println("Saque realizado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void transferir() {
        System.out.println("Digite o numero da conta de origem: ");
        Integer numContaOrigem = scanner.nextInt();
        System.out.println("Digite o numero da conta de destino: ");
        Integer numContaDestino = scanner.nextInt();
        System.out.println("Digite a senha: ");
        Integer senha = scanner.nextInt();
        System.out.println("Digite o valor a ser transferido: ");
        BigDecimal valor = scanner.nextBigDecimal();

        try {
            transacaoBancariaContaMembro.transferir(numContaOrigem, numContaDestino, senha, valor);
            System.out.println("Transferencia realizada com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
