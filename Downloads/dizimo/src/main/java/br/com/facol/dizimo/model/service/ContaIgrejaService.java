package br.com.facol.dizimo.model.service;

import br.com.facol.dizimo.model.entities.ContaIgreja;
import br.com.facol.dizimo.model.entities.ContaMembro;
import br.com.facol.dizimo.model.entities.Igreja;
import br.com.facol.dizimo.model.repository.ContaIgrejaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ContaIgrejaService {
    @Autowired
    private ContaIgrejaRepository contaIgrejaRepository;
    @Autowired
    private IgrejaService igrejaService;

    @Transactional
    public ContaIgreja cadastrarContaIgreja(String cnpj,Integer senha,ContaIgreja contaIgreja) {
        Optional<Igreja> igrejaEncontrada = null;
        try {
            igrejaEncontrada = Optional.ofNullable(igrejaService.listarIgreja(cnpj, senha).orElseThrow(() -> new Exception("Igreja não encontrada")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ContaIgreja contaIgrejaCadastrada = contaIgrejaRepository.save(contaIgreja);

        return contaIgrejaCadastrada;
    }

    public Optional<ContaIgreja> listarContaIgreja(Integer numDaConta, Integer senha) {
        try {
            return Optional.ofNullable(contaIgrejaRepository.findByNumeroDaContaAndSenhaAndAtivoIsTrue(numDaConta,senha).orElseThrow(() -> new Exception("Não foi encontrado nenhuma conta")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void excluirContaIgreja(Integer numDaConta, Integer senha) throws Exception {
        Optional<ContaIgreja> contaMembroEncontrada = Optional.ofNullable(contaIgrejaRepository.findByNumeroDaContaAndSenhaAndAtivoIsTrue(numDaConta, senha).orElseThrow(() -> new Exception("Conta não foi encontrada!")));

        contaMembroEncontrada.get().setAtivo(false);
    }

}
