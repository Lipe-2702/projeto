package br.com.facol.dizimo.model.service;

import br.com.facol.dizimo.model.entities.ContaMembro;
import br.com.facol.dizimo.model.entities.Membro;
import br.com.facol.dizimo.model.repository.ContaMembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ContaMembroService {
    @Autowired
    private ContaMembroRepository contaMembroRepository;
    @Autowired
    private MembroService membroService;

    @Transactional
    public ContaMembro cadastrarContaMembro(String cpf,Integer senha,ContaMembro contaMembro) {
        Optional<Membro> membroEncontrado = null;
        try {
            membroEncontrado = Optional.ofNullable(membroService.listarMembros(cpf, senha).orElseThrow(() -> new Exception("Membro não encontrado")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ContaMembro contaMembroCadastrada = contaMembroRepository.save(contaMembro);

        membroEncontrado.get().setContaMembro(contaMembroCadastrada);

        return contaMembroCadastrada;
    }

    public Optional<ContaMembro> listarContaIgreja(Integer numDaConta, Integer senha) {
        try {
            return Optional.ofNullable(contaMembroRepository.findByNumeroDaContaAndSenhaAndAtivoIsTrue(numDaConta,senha).orElseThrow(() -> new Exception("Não foi encontrado nenhuma conta")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void excluirContaMembro(Integer numDaConta, Integer senha) throws Exception {
        Optional<ContaMembro> contaMembroEncontrada = Optional.ofNullable(contaMembroRepository.findByNumeroDaContaAndSenhaAndAtivoIsTrue(numDaConta, senha).orElseThrow(() -> new Exception("Conta não foi encontrada!")));

        contaMembroEncontrada.get().setAtivo(false);
    }

    public void alterarContaMembro(Integer numDaConta, Integer senha, ContaMembro contaMembro) {

    }
}
