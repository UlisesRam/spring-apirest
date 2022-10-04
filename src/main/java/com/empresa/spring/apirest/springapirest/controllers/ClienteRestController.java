package com.empresa.spring.apirest.springapirest.controllers;

import com.empresa.spring.apirest.springapirest.models.entity.Cliente;
import com.empresa.spring.apirest.springapirest.models.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ClienteRestController {

    @Autowired
    private IClienteRepository clienteRepository;

    @GetMapping(value="/clientes/lista")
    public List<Cliente> getClientes(){
        return clienteRepository.findAll();
    }

    @GetMapping(value="/clientes/{id}")
    /* se coloca un tipo generico '?' para el manejo de errores puede devolver un Cliente, String, Collection, etc.*/
    public ResponseEntity<?> findById(@PathVariable String id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        /* Map para almacenar el nombre y el mensaje de error */
        /* Map es la interfaz, HashMap es la implementacion */
        Map<String, Object> response = new HashMap<>();

        if(!cliente.isPresent()) {
            response.put("mensaje", "El id: " .concat(id.concat( " no esta en la base de datos, puede que haya ingresado mal los datos.")));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // El response es el entity, y el estatus.
        }
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }
/**
 Spring Boot: Gestión de errores en aplicaciones web y REST
 https://danielme.com/2019/03/02/spring-boot-gestion-de-errores-en-aplicaciones-web-y-rest/
 */

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
