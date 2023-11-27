package br.com.facol.dizimo.model.service;

import br.com.facol.dizimo.model.entities.Endereco;
import br.com.facol.dizimo.model.entities.Membro;
import br.com.facol.dizimo.model.repository.MembroRepository;
import br.com.facol.dizimo.model.validation.IValidar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class MembroService {
    @Autowired
    private MembroRepository repository;
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private List<IValidar<Membro>> validador;

    @Transactional
    public Membro salvarMembro(Membro membro) {
        calcularIdade(membro);
        validador.forEach(v -> v.validar(membro));
        Optional<Endereco> enderecoEncontrado = Optional.ofNullable(enderecoService.listarEnderecoPorID(membro.getEndereco().getId()).orElseThrow(() -> new RuntimeException("Endereço não encontrado")));
        membro.setEndereco(enderecoEncontrado.get());
        return repository.save(membro);
    }

    private void calcularIdade(Membro membro) {
        LocalDate dataDeNasc = membro.getDataDeNascimento();
        LocalDate dataAtual = LocalDate.now();
        Integer idade = (int) ChronoUnit.YEARS.between(dataDeNasc, dataAtual);
        membro.setIdade(idade);
    }

    public Optional<Membro> listarMembros(String cpf,Integer senha) {
        try {
            return Optional.ofNullable(repository.findByCpfAndSenhaAndAtivoIsTrue(cpf, senha).orElseThrow(() -> new Exception("Membro não encontrado")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void alterarMembro(String cpf,Integer senha,Membro membroAtulizado) throws Exception {

        Optional<Membro> membroEncontrado = Optional.ofNullable(repository.getReferenceByCpfAndSenhaAndAtivoIsTrue(cpf,senha));

        if (!(membroEncontrado != null)) {
            throw new Exception("Não é possivel atualizar o membro pois não foi encontrado!");
        }

        if (membroAtulizado.getNome() != null) {
            membroEncontrado.get().setNome(membroAtulizado.getNome());
        }

        if (membroAtulizado.getEmail() != null) {
            membroEncontrado.get().setEmail(membroAtulizado.getEmail());
        }

        if (membroAtulizado.getSenha() != null) {
            membroEncontrado.get().setSenha(membroAtulizado.getSenha());
        }

        Optional<Endereco> enderecoEncontrado = enderecoService.listarEnderecoPorID(membroEncontrado.get().getEndereco().getId());

        if (!(enderecoEncontrado.isPresent())) {
            throw new Exception("Endereço não foi encontrado");
        }

        membroEncontrado.get().setEndereco(enderecoEncontrado.get());

    }

    @Transactional
    public void excluirMembro(String cpf,Integer senha) throws Exception {
        Membro membroEncontrado = repository.getReferenceByCpfAndSenhaAndAtivoIsTrue(cpf,senha);

        if (!membroEncontrado.getAtivo().booleanValue()) {
            throw new Exception("Não pode excluir esse membro pois ele não está ativo");
        }

        membroEncontrado.setAtivo(false);
    }
}
