package com.empresa.spring.apirest.springapirest.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Document
public class Cliente<C> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String nombre;
    private String apellido;
    @Indexed(unique = true)
    private String email;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS]")
    private LocalDateTime createAt;

    public Cliente(String nombre, String apellido, String email, LocalDateTime createAt) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.createAt = createAt;
    }
}
