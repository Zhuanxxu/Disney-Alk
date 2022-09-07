package com.alkemy.disney.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PersonajeFilterDTO {
    private Long Id;
    private String nombre;
    private Integer edad;
    private Long peso;
    private String orden;
    private List<Long> peliculas;


    public PersonajeFilterDTO(Long id, String nombre, Integer edad, Long peso, String orden, List<Long> peliculas) {
        Id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.orden = orden;
        this.peliculas = peliculas;
    }

    public boolean isASC(){
        return this.orden.compareToIgnoreCase("ASC")==0;
    }

    public boolean isDESC() {
        return this.orden.compareToIgnoreCase("DESC") == 0;
    }
}
