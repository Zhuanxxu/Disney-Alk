package com.alkemy.disney.dto;

import lombok.Data;

import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Data
public class PersonajeFilterDTO {

    private String nombre;
    private Integer edad;
    private String orden;
    private List<Long> peliculas;


    public PersonajeFilterDTO(String nombre, Integer edad, String orden, List<Long> peliculas) {
        this.nombre = nombre;
        this.edad = edad;
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
