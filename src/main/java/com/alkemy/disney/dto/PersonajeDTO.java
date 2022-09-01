package com.alkemy.disney.dto;

import lombok.Data;

@Data
public class PersonajeDTO {
    private Long Id;
    private String imagen;
    private String nombre;
    private int edad;
    private float peso;
    private String historia;

}
