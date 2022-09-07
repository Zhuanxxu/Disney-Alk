package com.alkemy.disney.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PersonajeBasicDTO {
    private Long Id;
    private String imagen;
    private String nombre;
    private Integer edad;
    private Long peso;
    private String historia;
}
