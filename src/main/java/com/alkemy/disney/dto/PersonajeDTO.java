package com.alkemy.disney.dto;

import com.alkemy.disney.entity.PeliculaSerieEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Data
public class PersonajeDTO {
    private Long Id;
    private String imagen;
    @NotNull @NotEmpty
    private String nombre;
    @Positive
    private Integer edad;
    @Positive
    private Long peso;
    private String historia;
    private List<PeliculaSerieDTO> peliculasSeries = new ArrayList<>();

}
