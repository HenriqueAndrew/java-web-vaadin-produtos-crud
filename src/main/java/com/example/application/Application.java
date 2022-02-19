package com.example.application;

import com.example.application.model.Produto;
import com.example.application.model.ProdutoRepository;
import java.time.LocalDate;
import java.time.Month;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public CommandLineRunner demo(ProdutoRepository repository) {
        return (args) -> {
            repository.save(new Produto("Refrigerante Coca-Cola 200 ml", "Bebida", "Garrafa", 1.39));
            repository.save(new Produto("Refrigerante Guaraná Antarctica 237 ml", "Bebida", "Garrafa", 1.09));
            repository.save(new Produto("Salgadinho Doritos Queijo Nacho 84g", "Salgadinho", "Pacote", 7.49));
            repository.save(new Produto("Salgadinho Ruffles Batata Frita Original 96g", "Salgadinho", "Pacote", 6.99));
            repository.save(new Produto("Biscoito Bauducco Wafer Maxi Chocolate 117g", "Biscoito", "Pacote", 2.49));
            repository.save(new Produto("Chocolate Alpino Nestlé 90g", "Chocolate", "Barra", 5.19));
        };
    }
   

}
