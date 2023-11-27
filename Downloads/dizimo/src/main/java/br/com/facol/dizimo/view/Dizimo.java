package br.com.facol.dizimo.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Dizimo {

    private static final int SAIR = 0;
    private static final int CADASTRAR_MEMBRO = 1;
    private static final int LISTAR_MEMBRO = 2;
    private static final int EXCLUIR_MEMBRO = 3;
    private static final int CADASTRAR_IGREJA = 4;
    private static final int LISTAR_IGREJA = 5;
    private static final int EXCLUIR_IGREJA = 6;
    private static final int CADASTRAR_CONTA_MEMBRO = 7;
    private static final int LISTAR_CONTA_MEMBRO = 8;
    private static final int EXCLUIR_CONTA_MEMBRO = 9;
    private static final int DEPOSITAR_IGREJA = 10;
    private static final int SACAR_IGREJA = 11;
    private static final int TRANSFERIR_IGREJA = 12;
    private static final int DEPOSITAR_MEMBRO = 13;
    private static final int SACAR_MEMBRO = 14;
    private static final int TRANSFERIR_MEMBRO = 15;

    private static Scanner scanner = new Scanner(System.in);

    @Autowired
    private MembroView membroView;

    @Autowired
    private IgrejaView igrejaView;

    @Autowired
    private ContaMembroView contaMembroView;

    @Autowired
    private TransacaoIgrejaView transacaoIgrejaView;

    @Autowired
    private TransacaoMembroView transacaoMembroView;

    public void exibirMenu() {
        int opcao;

        do {
            exibirOpcoes();
            System.out.print("Escolha uma opção (0 para sair): ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case CADASTRAR_MEMBRO:
                    membroView.cadastrarMembro();
                    break;
                case LISTAR_MEMBRO:
                    membroView.listarMembro();
                    break;
                case EXCLUIR_MEMBRO:
                    try {
                        membroView.excluirMembro();
                    } catch (Exception e) {
                        System.out.println("Erro ao excluir membro: " + e.getMessage());
                    }
                    break;
                case CADASTRAR_IGREJA:
                    igrejaView.cadastrarIgreja();
                    break;
                case LISTAR_IGREJA:
                    igrejaView.listarIgreja();
                    break;
                case EXCLUIR_IGREJA:
                    try {
                        igrejaView.excluirIgreja();
                    } catch (Exception e) {
                        System.out.println("Erro ao excluir igreja: " + e.getMessage());
                    }
                    break;
                case CADASTRAR_CONTA_MEMBRO:
                    contaMembroView.cadastrarContaMembro();
                    break;
                case LISTAR_CONTA_MEMBRO:
                    contaMembroView.listarContaMembro();
                    break;
                case EXCLUIR_CONTA_MEMBRO:
                    try {
                        contaMembroView.excluirContaMembro();
                    } catch (Exception e) {
                        System.out.println("Erro ao excluir conta do membro: " + e.getMessage());
                    }
                    break;
                case DEPOSITAR_IGREJA:
                    transacaoIgrejaView.depositar();
                    break;
                case SACAR_IGREJA:
                    transacaoIgrejaView.sacar();
                    break;
                case TRANSFERIR_IGREJA:
                    transacaoIgrejaView.transferir();
                    break;
                case DEPOSITAR_MEMBRO:
                    transacaoMembroView.depositar();
                    break;
                case SACAR_MEMBRO:
                    transacaoMembroView.sacar();
                    break;
                case TRANSFERIR_MEMBRO:
                    transacaoMembroView.transferir();
                    break;
                case SAIR:
                    System.out.println("Saindo do sistema.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != SAIR);
    }

    private void exibirOpcoes() {
        System.out.println("----------- MENU -----------");
        System.out.println("1. Cadastrar Membro");
        System.out.println("2. Listar Membro");
        System.out.println("3. Excluir Membro");
        System.out.println("4. Cadastrar Igreja");
        System.out.println("5. Listar Igreja");
        System.out.println("6. Excluir Igreja");
        System.out.println("7. Cadastrar Conta do Membro");
        System.out.println("8. Listar Conta do Membro");
        System.out.println("9. Excluir Conta do Membro");
        System.out.println("10. Depositar na Conta da Igreja");
        System.out.println("11. Sacar da Conta da Igreja");
        System.out.println("12. Transferir da Conta da Igreja");
        System.out.println("13. Depositar na Conta do Membro");
        System.out.println("14. Sacar da Conta do Membro");
        System.out.println("15. Transferir da Conta do Membro");
        System.out.println("0. Sair");
        System.out.println("---------------------------");
    }
}

