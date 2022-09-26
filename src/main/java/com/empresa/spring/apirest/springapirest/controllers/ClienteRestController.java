package com.empresa.spring.apirest.springapirest.controllers;

import com.empresa.spring.apirest.springapirest.models.entity.Cliente;
import com.empresa.spring.apirest.springapirest.models.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ClienteRestController {

    @Autowired
    private IClienteRepository clienteRepository;

    @GetMapping(value="/clientes")
    public List<Cliente> getClientes(){
        return clienteRepository.findAll();
    }

    @GetMapping(value="/clientes/{id}")
    public Optional<Cliente> findById(@PathVariable String id){
        return clienteRepository.findById(id);
    }

    @PutMapping(value="/update/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente update(@RequestBody Cliente cliente, @PathVariable String id){
        Cliente clienteActual = clienteRepository.findById(id).get();
                clienteActual.setNombre(cliente.getNombre());
                clienteActual.setApellido(cliente.getApellido());
                clienteActual.setEmail(cliente.getEmail());
                clienteActual.setCreateAt(cliente.getCreateAt());
        return clienteRepository.save(clienteActual);
    }

    @PostMapping(value ="/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveClientes(@RequestBody Cliente cliente){
        clienteRepository.save(cliente);
        return "Cliente agregado con Id: " + cliente.getId();
    }

    @DeleteMapping(value="/clientes/delete/{id}")
//    @ResponseStatus(HttpStatus.CREATED) -> no se mostrara el return
    public String deleteById(@PathVariable String id){
        clienteRepository.deleteById(id);
        return "Cliente borrado con el Id: " + id;
    }

}
