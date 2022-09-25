package com.empresa.spring.apirest.springapirest.models.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@Document
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String nombre;
    private String apellido;
    @Indexed(unique = true)
    private String email;
    private LocalDateTime createAt;

    public Cliente(String nombre, String apellido, String email, LocalDateTime createAt) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.createAt = createAt;
    }
}
