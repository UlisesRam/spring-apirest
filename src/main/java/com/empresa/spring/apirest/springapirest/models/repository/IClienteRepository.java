package com.empresa.spring.apirest.springapirest.models.repository;

import com.empresa.spring.apirest.springapirest.models.entity.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IClienteRepository extends MongoRepository<Cliente, String> {

    Optional<Cliente> findClienteByEmail(String email);

}
