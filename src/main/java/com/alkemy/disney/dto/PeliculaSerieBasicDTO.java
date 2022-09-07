package com.alkemy.disney.dto;

import com.alkemy.disney.entity.GeneroEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class PeliculaSerieBasicDTO {
    private Long Id;
    private String imagen;
    private String titulo;
    private LocalDate fechaCreacion;

}
