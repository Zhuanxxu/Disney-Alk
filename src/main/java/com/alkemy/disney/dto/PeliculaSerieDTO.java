package com.alkemy.disney.dto;

import com.alkemy.disney.entity.GeneroEntity;
import com.alkemy.disney.entity.PersonajeEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class PeliculaSerieDTO {
    private Long Id;
    private String imagen;
    private String titulo;
    private LocalDate fechaCreacion;
    private int calificacion;
    private List<PersonajeDTO> personajes = new ArrayList<>();
    private GeneroEntity genero;
}
