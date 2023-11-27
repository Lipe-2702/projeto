package br.com.facol.dizimo;

import br.com.facol.dizimo.model.entities.Endereco;
import br.com.facol.dizimo.model.entities.Membro;
import br.com.facol.dizimo.model.enums.Estado;
import br.com.facol.dizimo.model.service.MembroService;
import br.com.facol.dizimo.view.Dizimo;
import br.com.facol.dizimo.view.MembroView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DizimoApplication implements CommandLineRunner {
    @Autowired
    private Dizimo dizimo;
    public static void main(String[] args) {
        SpringApplication.run(DizimoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        dizimo.exibirMenu();
    }
}
