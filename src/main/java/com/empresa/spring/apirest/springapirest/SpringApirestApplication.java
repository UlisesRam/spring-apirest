package com.empresa.spring.apirest.springapirest;

import com.empresa.spring.apirest.springapirest.models.entity.Cliente;
import com.empresa.spring.apirest.springapirest.models.repository.IClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class SpringApirestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringApirestApplication.class, args);
    }

    @Bean
    CommandLineRunner runner (IClienteRepository repository, MongoTemplate mongoTemplate){
        return args -> {

            String email = "fab2@mail.com";
            Cliente cliente = new Cliente("Fabian2","Ramirez", email, LocalDateTime.now());

            // usingMongoTemplateAndQuery(repository, mongoTemplate, email, cliente);

            if(!repository.findClienteByEmail(email).isPresent()){
                System.out.println("Insertando cliente: " + cliente);
                repository.insert(cliente);
            }else{
                System.out.println(cliente + " ya se encuentra registrado");
            }
        };
    }



    /** usingMongoTemplateAndQuery */
    private void usingMongoTemplateAndQuery(IClienteRepository repository, MongoTemplate mongoTemplate, String email, Cliente cliente) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is("fabian@mail.com"));

        List<Cliente> clientes = mongoTemplate.find(query, Cliente.class);

        if(clientes.size() > 1 ){
            throw new IllegalStateException("El email " + email + " ya se encuentra registrado");
        }
        if(clientes.isEmpty()){
            System.out.println("Insertando cliente: " + cliente);
            repository.insert(cliente);
        }else{
            System.out.println(cliente + " ya se encuentra registrado");
        }
    }
}
