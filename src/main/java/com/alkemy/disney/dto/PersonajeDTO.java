package com.alkemy.disney.dto;

import com.alkemy.disney.entity.PeliculaSerieEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PersonajeDTO {
    private Long Id;
    private String imagen;
    private String nombre;
    private Integer edad;
    private Long peso;
    private String historia;
    private List<PeliculaSerieDTO> peliculasSeries = new ArrayList<>();

}
