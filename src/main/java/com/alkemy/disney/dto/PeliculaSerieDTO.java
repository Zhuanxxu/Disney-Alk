package com.alkemy.disney.dto;

import com.alkemy.disney.entity.GeneroEntity;
import com.alkemy.disney.entity.PersonajeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class PeliculaSerieDTO {
    private Long Id;
    private String imagen;
    @NotNull @NotEmpty
    private String titulo;
    //@Pattern(regexp="\\{4}-\\d{2}-\\d{2}", message = "El formato de fecha debe ser yyyy-MM-dd")
    private LocalDate fechaCreacion;
    @Min(1)
    @Max(5)
    private int calificacion;
    private List<PersonajeDTO> personajes = new ArrayList<>();

    private Long genero;
}
