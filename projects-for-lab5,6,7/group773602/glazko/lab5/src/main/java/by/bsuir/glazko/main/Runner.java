package by.bsuir.glazko.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("by.bsuir.glazko")
@EnableJpaRepositories("by.bsuir.glazko.repository")
public class Runner {

    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }
}