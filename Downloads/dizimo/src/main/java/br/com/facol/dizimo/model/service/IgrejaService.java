package br.com.facol.dizimo.model.service;

import br.com.facol.dizimo.model.entities.Endereco;
import br.com.facol.dizimo.model.entities.Igreja;
import br.com.facol.dizimo.model.repository.IgrejaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class IgrejaService {
    @Autowired
    private IgrejaRepository igrejaRepository;
    @Autowired
    private EnderecoService enderecoService;

    @Transactional
    public Igreja salvarIgreja(Igreja igreja) {
        Optional<Endereco> enderecoEncontrado = enderecoService.listarEnderecoPorID(igreja.getId());
        igreja.setEndereco(enderecoEncontrado.get());
        return igrejaRepository.save(igreja);
    }

    public Optional<Igreja> listarIgreja(String cnpj,Integer senha) {
        try {
            return Optional.ofNullable(igrejaRepository.findByCnpjAndSenhaAndAtivoIsTrue(cnpj,senha));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void excluirIgreja(String cnpj,Integer senha) throws Exception {
        Igreja igrejaEncontrada = igrejaRepository.getReferenceByCnpjAndSenhaAndAtivoIsTrue(cnpj,senha);

        if (!(igrejaEncontrada != null)) {
            throw new Exception("Igrja não encontrada, não é possivel fazer a exclusão");
        }

        igrejaEncontrada.setAtivo(false);
    }

    @Transactional
    public void alterarIgreja(String cnpj,Integer senha,Igreja igrejaAtualizada) throws Exception {
        Optional<Igreja> igrejaEncontrada = Optional.ofNullable(igrejaRepository.getReferenceByCnpjAndSenhaAndAtivoIsTrue(cnpj,senha));

        if (!(igrejaEncontrada != null)) {
            throw new Exception("Não é possivel atualizar a igrja pois não foi encontrado!");
        }

        if (igrejaAtualizada.getNomeDaIgreja() != null) {
            igrejaEncontrada.get().setNomeDaIgreja(igrejaAtualizada.getNomeDaIgreja());
        }

        if (igrejaAtualizada.getEmail() != null) {
            igrejaEncontrada.get().setEmail(igrejaAtualizada.getEmail());
        }

        if (igrejaAtualizada.getSenha() != null) {
            igrejaEncontrada.get().setSenha(igrejaAtualizada.getSenha());
        }

        Optional<Endereco> enderecoEncontrado = enderecoService.listarEnderecoPorID(igrejaEncontrada.get().getEndereco().getId());

        if (!(enderecoEncontrado.isPresent())) {
            throw new Exception("Endereço não foi encontrado");
        }

        igrejaEncontrada.get().setEndereco(enderecoEncontrado.get());
    }
}
