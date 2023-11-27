package br.com.facol.dizimo.model.service;

import br.com.facol.dizimo.model.entities.ContaIgreja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransacaoIgrejaService {
    @Autowired
    private ContaMembroService membroService;
    @Autowired
    private ContaIgrejaService igrejaService;

    @Transactional
    public void depositar(Integer numConta, Integer senha, BigDecimal valor) throws Exception {
        ContaIgreja contaIgrejaEncontrada = igrejaService.listarContaIgreja(numConta, senha).orElseThrow(() -> new Exception("Conta não encontrada"));
        try {
            BigDecimal saldoAtual = contaIgrejaEncontrada.getSaldo();
            BigDecimal novoSaldo = saldoAtual.add(valor);
            contaIgrejaEncontrada.setSaldo(novoSaldo);
        } catch (Exception e) {
            throw new Exception("Não foi possivel realizar o deposito!");
        }
    }

    @Transactional
    public void sacar(Integer numConta, Integer senha, BigDecimal valor) throws Exception {
        ContaIgreja contaIgrejaEncontrada = igrejaService.listarContaIgreja(numConta, senha).orElseThrow(() -> new Exception("Conta não encontrada"));
        try {
            BigDecimal saldoAtual = contaIgrejaEncontrada.getSaldo();
            if (valor.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("O valor do saque deve ser maior que zero");
            } else if (saldoAtual.compareTo(valor) >= 0) {
                BigDecimal novoSaldo = saldoAtual.subtract(valor);
                contaIgrejaEncontrada.setSaldo(novoSaldo);
            }
        } catch (Exception e) {
            throw new Exception("Não foi possivel realizar o saque!");
        }
    }

    @Transactional
    public void transferir(Integer numeroDaContaOrigem, Integer numeroDaContaDestino, Integer senha, BigDecimal valor) throws Exception {
        ContaIgreja contaIgrejaEncontrada = igrejaService.listarContaIgreja(numeroDaContaOrigem, senha).orElseThrow(() -> new Exception("Conta não encontrada"));
        ContaIgreja contaIgrejaEncontrada2 = igrejaService.listarContaIgreja(numeroDaContaDestino, senha).orElseThrow(() -> new Exception("Conta não encontrada"));
        try {
            BigDecimal saldoAtual = contaIgrejaEncontrada.getSaldo();
            if (valor.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("O valor da transferencia não pode ser 0");
            } else if (saldoAtual.compareTo(valor) >= 0) {
                BigDecimal novoSaldo = saldoAtual.subtract(valor);
                contaIgrejaEncontrada.setSaldo(novoSaldo);
                contaIgrejaEncontrada2.setSaldo(contaIgrejaEncontrada2.getSaldo().add(valor));
            }
        } catch (Exception e) {
            throw new Exception("Não foi possivel realizar a transferencia!");
        }
    }
}
